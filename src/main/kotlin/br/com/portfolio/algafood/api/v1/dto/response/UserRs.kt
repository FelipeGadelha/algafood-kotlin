package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.domain.entity.User
import com.fasterxml.jackson.annotation.JsonView

class UserRs(
    @JsonView(View.Basic::class) val id: Long,
    @JsonView(View.Basic::class) val name: String,
    val email: String
) {
    constructor(user: User):
        this(
        id = user.id!!,
        name = user.name,
        email = user.email
    )
}