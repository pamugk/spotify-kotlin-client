package spotify.api.domain

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Restrictions(
    val reason: Reason
) {
    @kotlinx.serialization.Serializable
    enum class Reason {
        @SerialName("market")
        MARKET,
        @SerialName("product")
        PRODUCT,
        @SerialName("explicit")
        EXPLICIT
    }
}
