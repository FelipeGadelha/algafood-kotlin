package br.com.portfolio.algafood.domain.filter

import org.springframework.format.annotation.DateTimeFormat
import java.time.OffsetDateTime


class DailySaleFilter(
    val restaurantId: Long,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val creationDate: OffsetDateTime,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val finishDate: OffsetDateTime
)