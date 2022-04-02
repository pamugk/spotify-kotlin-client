package spotify.api.domain

@kotlinx.serialization.Serializable
data class Image(
    /**
     * The source URL of the image.
     */
    val url: String,
    /**
     * The image height in pixels.
     */
    val height: Int,
    /**
     * The image width in pixels.
     */
    val weight: Int
)
