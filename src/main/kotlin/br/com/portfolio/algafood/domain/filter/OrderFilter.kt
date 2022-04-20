package br.com.portfolio.algafood.domain.filter

import br.com.portfolio.algafood.domain.entity.OrderStatusType
import org.springframework.format.annotation.DateTimeFormat
import java.time.OffsetDateTime

class OrderFilter(
    val clientId: Long?,
    val restaurantId: Long?,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val creationDate: OffsetDateTime?,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val finishDate: OffsetDateTime?,
    val status: OrderStatusType?
)