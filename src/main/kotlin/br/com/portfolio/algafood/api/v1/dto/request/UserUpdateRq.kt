package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class UserUpdateRq(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email val email: String
) {
    fun toEntity() = User(
        name = name,
        email = email
    )
}
