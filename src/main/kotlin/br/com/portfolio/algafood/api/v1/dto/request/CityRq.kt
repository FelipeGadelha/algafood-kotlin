package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.City
import br.com.portfolio.algafood.domain.entity.State
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


class CityRq(
    @field:NotBlank val name: String,
    @field:NotNull val stateId: Long
) {
    fun toEntity() = City(name = name, state = State(id = stateId))
}