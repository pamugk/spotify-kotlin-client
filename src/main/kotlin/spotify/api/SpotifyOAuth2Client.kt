package spotify.api

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.util.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title

typealias CIOClient = io.ktor.client.engine.cio.CIO

class SpotifyOAuth2Client(private val config: SpotifyClientConfiguration) {
    private companion object {
        private val stateAllowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        fun getRandomState(length: Int): String = (1..length).map { stateAllowedChars.random() }.joinToString("")
    }

    private val state = getRandomState(16)
    private var authorizationCode: String? = null
    private var accessToken: String? = null
    private var refreshToken: String? = null

    private val redirectUrl
        get() = url{
            protocol = URLProtocol.HTTP
            host = "localhost"
            port = config.callbackPort
            path(config.callbackContext)
        }

    val loginUrl
        get() = url {
            protocol = URLProtocol.HTTPS
            host = "accounts.spotify.com"
            path("authorize")
            parameters.append("response_type", "code")
            parameters.append("client_id", config.clientId)
            parameters.append("scope", config.scope.joinToString(" "))
            parameters.append("redirect_uri", redirectUrl)
            parameters.append("state", state)
        }

    @OptIn(InternalAPI::class)
    private val fullSecret
        get() = "${config.clientId}:${config.clientSecret}".encodeBase64()

    private enum class GrantType(val value: String) {
        AUTHORIZATION_CODE("authorization_code"),
        REFRESH_TOKEN("refresh_token"),
        CLIENT_CREDENTIALS("client_credentials");
    }

    private fun makeTokenRequestBuilder(grantType: GrantType): HttpRequestBuilder.() -> Unit = {
        url {
            protocol = URLProtocol.HTTPS
            host = "accounts.spotify.com"
            path("api", "token")
        }
        formData {
            parameter("grant_type", grantType.value)
            when (grantType) {
                GrantType.AUTHORIZATION_CODE -> {
                    parameter("code", authorizationCode)
                    parameter("redirect_uri", redirectUrl)
                }
                GrantType.REFRESH_TOKEN -> TODO()
                GrantType.CLIENT_CREDENTIALS -> TODO()
            }

        }
        headers {
            append("Authorization", "Basic $fullSecret")
        }
    }

    val authenticationStatusChanged = Event<Boolean>()

    private val codeServer = embeddedServer(CIO, port = 8098) {
        routing {
            get("/callback") {
                if (state != call.parameters["state"]) {
                    call.respondRedirect("blocked")
                } else {
                    val authError = call.parameters["error"]
                    if (call.parameters["code"] == null || authError != null) {
                        call.respondRedirect("error")
                    } else {
                        authorizationCode = call.parameters["code"]!!
                        authenticationStatusChanged(true)
                        call.respondRedirect("success")
                    }
                }
            }
            get("/blocked") {
                call.respondHtml(HttpStatusCode.Forbidden) {
                    head {
                        title {
                            +"Неофициальный клиент Spotify"
                        }
                    }
                    body {
                        h1 {
                            +"Замечено вмешательство в процесс входа, доступ запрещён"
                        }
                    }
                }
            }
            get("/error") {
                call.respondHtml(HttpStatusCode.BadRequest) {
                    head {
                        title {
                            +"Неофициальный клиент Spotify"
                        }
                    }
                    body {
                        h1 {
                            +"Отказано в доступе"
                        }
                    }
                }
            }
            get("/success") {
                call.respondHtml(HttpStatusCode.OK) {
                    head {
                        title {
                            +"Неофициальный клиент Spotify"
                        }
                    }
                    body {
                        h1 {
                            +"Вход успешно завершён, можете возвращаться в приложение"
                        }
                    }
                }
            }
        }
    }.start()

    private val tokenClient = HttpClient(CIOClient){
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    val client = HttpClient(CIOClient) {
        defaultRequest {
            host = "api.spotify.com"
        }
        install(Auth) {
            bearer {
                loadTokens {
                    val tokenInfo = tokenClient.use {
                        it.submitForm<TokenInfo>(block = makeTokenRequestBuilder(
                            if (authorizationCode == null) GrantType.CLIENT_CREDENTIALS
                            else GrantType.AUTHORIZATION_CODE)
                        )
                    }
                    accessToken = tokenInfo.accessToken
                    refreshToken = tokenInfo.refreshToken
                    BearerTokens(
                        accessToken = tokenInfo.accessToken,
                        refreshToken = tokenInfo.refreshToken.orEmpty()
                    )
                }
                refreshTokens {
                    val tokenInfo = tokenClient.use {
                        it.submitForm<TokenInfo>(block = makeTokenRequestBuilder(
                            if (authorizationCode == null) GrantType.CLIENT_CREDENTIALS
                            else GrantType.REFRESH_TOKEN))
                    }
                    BearerTokens(
                        accessToken = tokenInfo.accessToken,
                        refreshToken = refreshToken.orEmpty()
                    )
                }
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    fun logOut() {
        authorizationCode = null
        accessToken = null
        refreshToken = null
        authenticationStatusChanged(false)
    }
}