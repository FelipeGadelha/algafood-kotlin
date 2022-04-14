package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.domain.entity.State

class StateRs(
    val id: Long,
    val name: String
) {
    constructor(state: State) :
        this(
            id = state.id!!,
            name = state.name,
        )
}
