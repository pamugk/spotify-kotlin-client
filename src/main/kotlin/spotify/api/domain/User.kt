package spotify.api.domain

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ExplicitContentConfig(
    /**
     * When <strong>true</strong>, indicates that explicit content should not be played.
     */
    val filterEnabled: Boolean,
    /**
     * When <strong>true</strong>, indicates that the explicit content setting is locked and can't be changed by the user.
     */
    val filterLocked: Boolean)

@kotlinx.serialization.Serializable
/**
 * A user
 */
data class User(
    /**
     * The country of the user, as set in the user's account profile.
     * An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code.
     * This field is only available when the current user has granted access to the <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#list-of-scopes">user-read-private</a> scope.
     */
    val country: String?,
    /**
     * The name displayed on the user's profile. <strong>null</strong> if not available.
     */
    @SerialName("display_name")
    val displayedName: String?,
    /**
     * The user's email address, as entered by the user when creating their account.
     * Important! This email address is unverified; there is no proof that it actually belongs to the user.
     * This field is only available when the current user has granted access to the <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#list-of-scopes">user-read-email</a> scope.
     */
    val email: String?,
    /**
     * The user's explicit content settings.
     * This field is only available when the current user has granted access to the user-read-private scope.
     */
    @SerialName("explicit_content")
    val explicitContentConfig: ExplicitContentConfig?,
    /**
     * Known external URLs for this user.
     */
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    /**
     * Information about the followers of the user.
     */
    val followers: Followers,
    /**
     * A link to the Web API endpoint for this user.
     */
    val href: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a> for the user.
     */
    val id: String,
    /**
     * The image width in pixels.
     */
    val images: List<Image>,
    /**
     * The user's Spotify subscription level: "premium", "free", etc.
     * (The subscription level "open" can be considered the same as "free".)
     * This field is only available when the current user has granted access to the <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#list-of-scopes">user-read-private</a> scope.
     */
    val product: String?,
    /**
     * The object type: "user"
     */
    val type: String,
    /**
     * The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the user.
     */
    val url: String
)
