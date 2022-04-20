package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class UserRq(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val password: String
) {
    fun toEntity(): User {
        return User(
            name = name,
            email = email,
            password = password
        )

    }
//    private String encrypt(String password) {
//        return new BCryptPasswordEncoder().encode(password);
//    }
}