package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.Group
import javax.validation.constraints.NotBlank

class GroupRq(
    @field:NotBlank val name: String
) {
    fun toEntity() = Group(name = name)
}

