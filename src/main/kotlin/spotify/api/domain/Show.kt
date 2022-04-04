package spotify.api.domain

import kotlinx.serialization.SerialName
import spotify.api.common.Page

@kotlinx.serialization.Serializable
@SerialName("show")
/**
 * A show
 */
data class Show(
    @SerialName("available_markets")
    /**
     * A list of the countries in which the show can be played, identified by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.
     */
    val availableMarkets: List<String>,
    /**
     * The copyright statements of the show.
     */
    val copyrights: List<Copyright>,
    /**
     * A description of the show.
     * HTML tags are stripped away from this field, use {@link #htmlDescription} field in case HTML tags are needed.
     */
    val description: String,
    @SerialName("html_description")
    /**
     * A description of the show.
     * This field may contain HTML tags.
     */
    val htmlDescription: String,
    /**
     * Whether or not the show has explicit content (true = yes it does; false = no it does not OR unknown).
     */
    val explicit: Boolean,
    @SerialName("external_urls")
    /**
     * External URLs for this show.
     */
    val externalUrls: ExternalUrls,
    /**
     * A link to the Web API endpoint providing full details of the show.
     */
    override val href: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.
     */
    override val id: String,
    /**
     * The cover art for the show in various sizes, widest first.
     */
    val images: List<Image>,
    @SerialName("is_externally_hosted")
    /**
     * True if all of the shows episodes are hosted outside of Spotify's CDN.
     * This field might be <strong>null</strong> in some cases.
     */
    val externallyHosted: Boolean?,
    /**
     * A list of the languages used in the show, identified by their <a href="https://en.wikipedia.org/wiki/ISO_639">ISO 639</a> code.
     */
    val languages: List<String>,
    @SerialName("media_type")
    /**
     * The media type of the show.
     */
    val mediaType: String,
    /**
     * The name of the episode.
     */
    val name: String,
    /**
     * The publisher of the show.
     */
    val publisher: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the show.
     */
    override val uri: String,
    /**
     * The episodes of the show.
     */
    val episodes: Page<Episode>
): Entity() {
    @kotlinx.serialization.Serializable
    data class Copyright(
        /**
         * The copyright text for this content.
         */
        val text: String,
        /**
         * The type of copyright:
         */
        val type: String
    ) {
        @kotlinx.serialization.Serializable
        enum class Type {
            @SerialName("C")
            COPYRIGHT,
            @SerialName("P")
            PERFORMANCE
        }
    }
}
