package spotify.api.common

@kotlinx.serialization.Serializable
data class Page<T>(
    /**
     * A link to the Web API endpoint returning the full result of the request
     */
    val href: String,
    /**
     * A link to the Web API endpoint returning the full result of the request
     */
    val items: List<T>,
    /**
     * The maximum number of items in the response (as set in the query or by default).
     */
    val limit: Int,
    /**
     * URL to the next page of items. ( <strong>null</strong> if none)
     */
    val next: String?,
    /**
     * The offset of the items returned (as set in the query or by default)
     */
    val offset: Int,
    /**
     * URL to the previous page of items. ( <strong>null</strong> if none)
     */
    val previous: String?,
    /**
     * The total number of items available to return.
     */
    val total: Int
)