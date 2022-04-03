package spotify.api

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class TokenInfo(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String?)