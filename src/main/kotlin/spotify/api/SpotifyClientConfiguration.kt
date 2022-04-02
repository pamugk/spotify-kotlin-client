package spotify.api

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SpotifyClientConfiguration(
    @SerialName("callback_context")
    val callbackContext: String,
    @SerialName("callback_port")
    val callbackPort: Int,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String,
    val scope: List<String>
)
