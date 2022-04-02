package spotify.api

@kotlinx.serialization.Serializable
internal data class TokenInfo(
    val accessToken: String, val tokenType: String, val scope: String,
    val expiresIn: Int, val refreshToken: String?)