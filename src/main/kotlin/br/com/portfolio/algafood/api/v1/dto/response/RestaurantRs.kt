package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.domain.entity.Restaurant
import java.math.BigDecimal


class RestaurantRs private constructor(
    val id: Long?,
    val name: String,
    val taxFreight: BigDecimal,
    val open: Boolean
) {
    constructor(restaurant: Restaurant):
        this(
            id = restaurant.id,
            name = restaurant.name,
            taxFreight = restaurant.taxFreight,
            open = restaurant.open
        )
}