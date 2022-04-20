package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IUserRepository: ICustomJpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>
}