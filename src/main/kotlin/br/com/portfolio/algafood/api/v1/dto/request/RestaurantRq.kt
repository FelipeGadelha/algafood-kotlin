package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.Address
import br.com.portfolio.algafood.domain.entity.Kitchen
import br.com.portfolio.algafood.domain.entity.Restaurant
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@Validated
class RestaurantRq(
    @field:NotBlank val name: String,
    @field:NotNull @field:PositiveOrZero /*@field:TaxFreight*/ val taxFreight: BigDecimal,
    @field:NotNull @field:Positive val kitchenId: Long,
    @field:Valid @field:NotNull val address: AddressRq
) {
    fun toEntity(): Restaurant {
        return Restaurant(
            name = name,
            taxFreight = taxFreight,
            kitchen = (Kitchen(kitchenId)),
            address = address.toEntity()
        )
    }
}