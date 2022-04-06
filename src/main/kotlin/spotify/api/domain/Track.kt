package spotify.api.domain

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
@SerialName("track")
/**
 * A track
 */
data class Track(
    /**
     * The album on which the track appears.
     * The album object includes a link in [Album.href] href to full information about the album.
     */
    val album: Album?,
    /**
     * The album on which the track appears.
     * The album object includes a link in [Artist.href] to full information about the album.
     */
    val artists: List<Artist>,
    @SerialName("available_markets")
    /**
     * The markets in which the track is available: [ISO 3166-1 alpha-2](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) country codes.
     */
    val availableMarkets: List<String>,
    @SerialName("disc_number")
    /**
     * The disc number (usually 1 unless the album consists of more than one disc).
     */
    val discNumber: Int,
    @SerialName("duration_ms")
    /**
     * The track length in milliseconds.
     */
    val duration: Int,
    /**
     * Whether or not the track has explicit lyrics ( <strong>true</strong> = yes it does; <strong>false</strong> = no it does not OR unknown).
     */
    val explicit: Boolean,
    @SerialName("external_ids")
    /**
     * Known external IDs for the track.
     */
    val externalIds: Ids,
    @SerialName("external_urls")
    /**
     * A link to the Web API endpoint providing full details of the track.
     */
    val externalUrls: ExternalUrls,
    /**
     * A link to the Web API endpoint providing full details of the track.
     */
    override val href: String,
    /**
     * The [Spotify ID](https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids) for the track.
     */
    override val id: String,
    @SerialName("is_playable")
    /**
     * Part of the response when [Track Relinking](https://developer.spotify.com/documentation/general/guides/track-relinking-guide/) is applied.
     * If <strong>true</a>, the track is playable in the given market.
     * Otherwise <strong>false</strong>.
     */
    val playable: Boolean,
    @SerialName("linked_from")
    /**
     * Part of the response when [Track Relinking](https://developer.spotify.com/documentation/general/guides/track-relinking-guide/) is applied, and the requested track has been replaced with different track.
     * The track in the linked_from object contains information about the originally requested track.
     */
    val linkedFrom: Track?,
    /**
     * Included in the response when a content restriction is applied.
     * See [Restriction Object](https://developer.spotify.com/documentation/web-api/reference/#object-trackrestrictionobject) for more details.
     */
    val restrictions: Restrictions?,
    /**
     * The name of the track.
     */
    val name: String,
    /**
     * The popularity of the track.
     * The value will be between 0 and 100, with 100 being the most popular.
     * The popularity of a track is a value between 0 and 100, with 100 being the most popular.
     * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.
     * Generally speaking, songs that are being played a lot now will have a higher popularity than songs that were played a lot in the past.
     * Duplicate tracks (e.g. the same track from a single and an album) are rated independently.
     * Artist and album popularity is derived mathematically from track popularity.
     * <strong>Note</strong>: the popularity value may lag actual popularity by a few days: the value is not updated in real time.
     */
    val popularity: Int,
    @SerialName("preview_url")
    /**
     * A link to a 30 second preview (MP3 format) of the track.
     * Can be <strong>null</strong>
     */
    val previewUrl: String?,
    @SerialName("track_number")
    /**
     * The number of the track.
     * If an album has several discs, the track number is the number on the specified disc.
     */
    val trackNumber: Int,
    /**
     * The [Spotify URI](https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids) for the track.
     */
    override val uri: String,
    @SerialName("is_local")
    /**
     * Whether or not the track is from a local file.
     */
    val local: Boolean
): Entity() {
    @kotlinx.serialization.Serializable
    data class Ids(
        val isrc: String?,
        val ean: String?,
        val upc: String?
    )
}
