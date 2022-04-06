package spotify.api.domain

@kotlinx.serialization.Serializable
data class ExternalUrls(
    /**
     * The [Spotify URL](https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids) for the object.
     */
    val spotify: String)
