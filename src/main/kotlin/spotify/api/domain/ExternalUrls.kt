package spotify.api.domain

@kotlinx.serialization.Serializable
data class ExternalUrls(
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URL</a> for the object.
     */
    val spotify: String)
