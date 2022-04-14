package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.domain.entity.City
import com.fasterxml.jackson.annotation.JsonView

class CityRs private constructor(
        @JsonView(View.Basic::class) val id: Long,
        @JsonView(View.Basic::class) val name: String,
        val state: StateRs
) {
    constructor(city: City) :
        this(
            id = city.id!!,
            name = city.name,
            state = StateRs(city.state)
        )
}
