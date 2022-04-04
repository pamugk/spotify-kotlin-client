package spotify.api.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import spotify.api.common.Page



@kotlinx.serialization.Serializable
@SerialName("album")
/**
 * An album
 */
data class Album(
    @SerialName("album_type")
    /**
     * The type of the album.
     */
    val albumType: Type,
    @SerialName("total_tracks")
    /**
     * The number of tracks in the album.
     */
    val totalTracks: Int,
    @SerialName("available_markets")
    /**
     * The markets in which the album is available: <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country codes.
     * NOTE: an album is considered available in a market when at least 1 of its tracks is available in that market.
     */
    val availableMarkets: List<String>,
    @SerialName("external_urls")
    /**
     * Known external URLs for this album.
     */
    val externalUrls: ExternalUrls,
    /**
     * A link to the Web API endpoint providing full details of the album.
     */
    override val href: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the album.
     */
    override val id: String,
    /**
     * The cover art for the album in various sizes, widest first.
     */
    val images: List<Image>,
    /**
     * The name of the album. In case of an album takedown, the value may be an empty string.
     */
    val name: String,
    @SerialName("release_date")
    /**
     * The date the album was first released.
     */
    val releaseDate: LocalDate,
    @SerialName("release_date_precision")
    /**
     * The precision with which {@link #releaseDate} value is known.
     */
    val releaseDatePrecision: DatePrecision,
    /**
     * Included in the response when a content restriction is applied.
     */
    val restrictions: Restrictions?,
    /**
     * The object type.
     */
    val type: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the album.
     */
    override val uri: String,
    /**
     * The artists of the album.
     * Each artist object includes a link in {@link Artist#href} to more detailed information about the artist.
     */
    val artists: List<Artist> = listOf(),
    /**
     * The tracks of the album.
     */
    val tracks: Page<Track>
): Entity() {
    @kotlinx.serialization.Serializable
    enum class Type{
        @SerialName("album")
        ALBUM,
        @SerialName("single")
        SINGLE,
        @SerialName("compilation")
        COMPILATION
    }
}
