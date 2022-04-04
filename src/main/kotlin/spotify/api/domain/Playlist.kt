package spotify.api.domain

import kotlinx.serialization.SerialName
import spotify.api.common.Page

@kotlinx.serialization.Serializable
@SerialName("playlist")
/**
 * A playlist
 */
data class Playlist(
    /**
     * <strong>true</strong> if the owner allows other users to modify the playlist.
     */
    val collaborative: Boolean,
    /**
     * The playlist description. Only returned for modified, verified playlists, otherwise <strong>null</strong>.
     */
    val description: String?,
    @SerialName("external_urls")
    /**
     * Known external URLs for this playlist.
     */
    val externalUrls: ExternalUrls,
    /**
     * Information about the followers of the playlist.
     */
    val followers: Followers,
    /**
     * A link to the Web API endpoint providing full details of the playlist.
     */
    override val href: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.
     */
    override val id: String,
    /**
     * Images for the playlist.
     * The array may be empty or contain up to three images.
     * The images are returned by size in descending order.
     * See <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/">Working with Playlists</a>.
     * <strong>Note</strong>: If returned, the source URL for the image ({@link Image#url}) is temporary and will expire in less than a day.
     */
    val images: List<Image>,
    /**
     * The name of the playlist.
     */
    val name: String,
    /**
     * The user who owns the playlist
     */
    val owner: User,
    /**
     * The playlist's public/private status: <strong>true</strong> the playlist is public, <strong>false</strong> the playlist is private, null the playlist status is not relevant.
     * For more about public/private status, see <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/">Working with Playlists</a>
     */
    val public: Boolean,
    @SerialName("snapshot_id")
    /**
     * The version identifier for the current playlist.
     * Can be supplied in other requests to target a specific playlist version
     */
    val snapshotId: String?,
    /**
     * The tracks of the playlist.
     */
    val tracks: Page<Track>,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the playlist.
     */
    override val uri: String
): Entity()
