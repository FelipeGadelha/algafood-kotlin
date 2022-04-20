package br.com.portfolio.algafood.domain.entity

import java.time.OffsetDateTime
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotNull


@Embeddable
class OrderStatus(
    @Enumerated(EnumType.STRING)
    val status: OrderStatusType,
    val moment: OffsetDateTime = OffsetDateTime.now()
) : Comparable<OrderStatus?> {

    override fun compareTo(@NotNull other: OrderStatus?): Int {
        return status.compareTo(other!!.status)
    }
}