package spotify.api.service

import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import spotify.api.SpotifyOAuth2Client
import spotify.api.domain.User

fun SpotifyOAuth2Client.getCurrentUser(): User = runBlocking {
    this@getCurrentUser.client.get<User> {
        url {
            encodedPath = "/v1/me"
        }
    }
}