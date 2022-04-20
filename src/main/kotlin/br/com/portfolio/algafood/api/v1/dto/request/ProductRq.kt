package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.Product
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

class ProductRq(
    @field:NotBlank val name: String,
    @field:NotBlank val description: String,
    @field:NotNull @field:PositiveOrZero val price: BigDecimal,
    @field:NotNull val active: Boolean
) {
    fun toEntity() = Product(
        name = name,
        description = description,
        price = price,
        active = active
    )
}
