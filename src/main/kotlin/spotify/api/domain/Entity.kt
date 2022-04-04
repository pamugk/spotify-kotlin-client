package spotify.api.domain

@kotlinx.serialization.Serializable
sealed class Entity {
    abstract val href: String
    abstract val id: String
    abstract val uri: String
}
