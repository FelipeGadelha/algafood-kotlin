package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.State
import javax.validation.constraints.NotBlank

class StateRq(
    @field:NotBlank val name: String
) {
    fun toEntity() = State(name = name)
}
