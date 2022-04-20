package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.domain.entity.Permission
import com.fasterxml.jackson.annotation.JsonView

class PermissionRs(
    @JsonView(View.Basic::class) val id: Long,
    @JsonView(View.Basic::class) val name: String,
    val description: String
) {
    constructor(permission: Permission):
        this(
            id = permission.id!!,
            name = permission.name,
            description = permission.description
        )
}
