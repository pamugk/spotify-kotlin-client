package spotify.api.domain

@kotlinx.serialization.Serializable
data class Followers(
    /**
     * This will always be set to null, as the Web API does not support it at the moment.
     */
    val href: String?,
    /**
     * The total number of followers.
     */
    val total: Int
)
