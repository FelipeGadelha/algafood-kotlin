package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.domain.entity.Kitchen
import com.fasterxml.jackson.annotation.JsonView

class KitchenRs private constructor(
        @JsonView(View.Basic::class) val id: Long?,
        @JsonView(View.Basic::class) val name: String
) {
    constructor(kitchen: Kitchen) :
        this(
            id = kitchen.id,
            name = kitchen.name
        )
}
