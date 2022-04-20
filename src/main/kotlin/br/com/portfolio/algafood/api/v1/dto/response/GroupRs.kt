package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.domain.entity.Group

class GroupRs private constructor(
    val id: Long?,
    val name: String
) {
    constructor(group: Group) :
        this(
            id = group.id,
            name = group.name
        )
}