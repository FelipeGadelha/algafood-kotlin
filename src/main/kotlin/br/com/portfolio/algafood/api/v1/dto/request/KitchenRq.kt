package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.Kitchen
import javax.validation.constraints.NotBlank

class KitchenRq(
    @field:NotBlank val name: String
) {
    fun toEntity() = Kitchen
        .Builder()
        .name(name)
        .build()
}
