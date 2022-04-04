package spotify.api.domain

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class DatePrecision {
    @SerialName("year")
    YEAR,
    @SerialName("month")
    MONTH,
    @SerialName("day")
    DAY
}