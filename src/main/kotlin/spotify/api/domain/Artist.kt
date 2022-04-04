package spotify.api.domain

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
@SerialName("artist")
/**
 * An artist
 */
data class Artist(
    @SerialName("external_urls")
    /**
     * Known external URLs for this artist.
     */
    val externalUrls: ExternalUrls,
    /**
     * Information about the followers of the artist.
     */
    val followers: Followers,
    /**
     * A list of the genres the artist is associated with. If not yet classified, the array is empty.
     */
    val genres: List<String>,
    /**
     * A link to the Web API endpoint providing full details of the artist.
     */
    override val href: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.
     */
    override val id: String,
    /**
     * Images of the artist in various sizes, widest first.
     */
    val images: List<Image>,
    /**
     * The name of the artist.
     */
    val name: String,
    /**
     * The popularity of the artist.
     * The value will be between 0 and 100, with 100 being the most popular. The artist's popularity is calculated from the popularity of all the artist's tracks.
     */
    val popularity: Int,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the artist.
     */
    override val uri: String
): Entity()
