package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.domain.entity.Product
import com.fasterxml.jackson.annotation.JsonView
import java.math.BigDecimal

class ProductRs private constructor(
    @JsonView(View.Basic::class) val id: Long,
    @JsonView(View.Basic::class) val name: String,
    val description: String,
    @JsonView(View.Basic::class) val price: BigDecimal,
    val active: Boolean
) {
    constructor(product: Product) :
        this(
            id = product.id!!,
            name = product.name,
            description = product.description,
            price = product.price,
            active = product.active
        )
}
