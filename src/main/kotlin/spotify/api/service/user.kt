package spotify.api.service

import io.ktor.client.request.*
import spotify.api.SpotifyOAuth2Client
import spotify.api.domain.User

suspend fun SpotifyOAuth2Client.getCurrentUser(): User =
    client.get {
        url {
            encodedPath = "/v1/me"
        }
    }