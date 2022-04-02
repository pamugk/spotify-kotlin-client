package spotify.api

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.html.*
import io.ktor.http.*
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
    private lateinit var authorizationCode: String
    private lateinit var tokenInfo: TokenInfo

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

    private fun makeTokenRequestBuilder(tokenType: String): HttpRequestBuilder.() -> Unit = {
        url {
            protocol = URLProtocol.HTTPS
            host = "accounts.spotify.com"
            path("spotify/api", "token")
        }
        formData {
            append("grant_type", tokenType)
            append("code", authorizationCode)
            append("client_id", config.clientId)
            append("redirect_uri", redirectUrl)
        }
        headers {
            append("Authorization", "Authorization: Basic $fullSecret")
        }
    }

    val authenticationStatusChanged = Event<Boolean>()

    private val codeServer = embeddedServer(CIO, port = 8098) {
        routing {
            get("/callback") {
                if (state != call.parameters["state"]) {
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
                } else {
                    val authError = call.parameters["error"]
                    if (call.parameters["code"] == null || authError != null) {
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
                    } else {
                        authorizationCode = call.parameters["code"]!!
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
                        authenticationStatusChanged(true)
                    }
                }
            }
        }
    }.start()

    private val tokenClient = HttpClient(CIOClient){
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    private val apiClient = HttpClient(CIOClient) {
        install(Auth) {
            bearer {
                loadTokens {
                    tokenInfo = tokenClient.use {
                        it.submitForm(block = makeTokenRequestBuilder("authorization_code"))
                    }
                    BearerTokens(
                        accessToken = tokenInfo.accessToken,
                        refreshToken = tokenInfo.refreshToken!!
                    )
                }
                refreshTokens {
                    tokenInfo = tokenClient.use {
                        it.submitForm(block = makeTokenRequestBuilder("refresh_token"))
                    }
                    BearerTokens(
                        accessToken = tokenInfo.accessToken,
                        refreshToken = tokenInfo.refreshToken!!
                    )
                }
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}