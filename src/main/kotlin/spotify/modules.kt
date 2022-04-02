package spotify

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.koin.dsl.module
import spotify.api.SpotifyOAuth2Client

val appModule = module {
    single { SpotifyOAuth2Client(Json.decodeFromStream(this::class.java.getResourceAsStream("/settings.json")!!)) }
}