package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.Permission
import javax.validation.constraints.NotBlank

class PermissionRq(
    @field:NotBlank val name: String,
    @field:NotBlank val description: String
) {
    fun toEntity() = Permission(
        name = name,
        description = description
    )
}
