package spotify.api.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
@SerialName("episode")
/**
 * An episode
 */
data class Episode(
    @SerialName("audio_preview_url")
    /**
     * A URL to a 30 second preview (MP3 format) of the episode. <strong>null</a> if not available.
     */
    val previewUrl: String?,
    /**
     * A description of the episode.
     * HTML tags are stripped away from this field, use {@link #htmlDescription} field in case HTML tags are needed.
     */
    val description: String,
    @SerialName("html_description")
    /**
     * A description of the episode.
     * This field may contain HTML tags.
     */
    val htmlDescription: String,
    @SerialName("duration_ms")
    /**
     * The episode length in milliseconds.
     */
    val duration: Int,
    /**
     * Whether or not the episode has explicit content (true = yes it does; false = no it does not OR unknown).
     */
    val explicit: Boolean,
    @SerialName("external_urls")
    /**
     * External URLs for this episode.
     */
    val externalUrls: ExternalUrls,
    /**
     * A link to the Web API endpoint providing full details of the episode.
     */
    override val href: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the episode.
     */
    override val id: String,
    /**
     * The cover art for the episode in various sizes, widest first.
     */
    val images: List<Image>,
    @SerialName("is_externally_hosted")
    /**
     * True if the episode is hosted outside of Spotify's CDN.
     */
    val externallyHosted: Boolean,
    @SerialName("is_playable")
    /**
     * True if the episode is playable in the given market.
     * Otherwise false.
     */
    val playable: Boolean,
    /**
     * The language used in the episode, identified by a ISO 639 code.
     * This field is deprecated and might be removed in the future.
     * Please use the {@link #languages} field instead.
     */
    val language: String,
    /**
     * A list of the languages used in the episode, identified by their <a href="https://en.wikipedia.org/wiki/ISO_639">ISO 639-1</a> code.
     */
    val languages: List<String>,
    /**
     * The name of the episode.
     */
    val name: String,
    @SerialName("release_date")
    /**
     * The date the episode was first released, for example <strong>"1981-12-15"</strong>.
     * Depending on the precision, it might be shown as <strong>"1981"</strong> or <strong>"1981-12"</strong>.
     */
    val releaseDate: LocalDate,
    @SerialName("release_date_precision")
    /**
     * The precision with which {@link #release_date} value is known.
     */
    val releaseDatePrecision: DatePrecision,
    @SerialName("resume_point")
    /**
     * The user's most recent position in the episode.
     * Set if the supplied access token is a user token and has the scope <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#list-of-scopes">user-read-playback-position</a>.
     */
    val resumePoint: ResumePoint?,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the album.
     */
    override val uri: String,
    /**
     * Included in the response when a content restriction is applied.
     * See <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episoderestrictionobject">Restriction Object</a> for more details.
     */
    val restrictions: Restrictions,
    val show: Show
): Entity() {
    @kotlinx.serialization.Serializable
    data class ResumePoint(
        @SerialName("fully_played")
        /**
         * Whether or not the episode has been fully played by the user.
         */
        val fullyPlayed: Boolean,
        @SerialName("resume_position_ms")
        /**
         * The user's most recent position in the episode in milliseconds.
         */
        val resumePosition: Int
    )
}
