package br.com.portfolio.algafood.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.Optional

@NoRepositoryBean
interface ICustomJpaRepository<T, ID>: JpaRepository<T, ID> {
    fun findFirstRegister(): Optional<T>
    fun detach(entity: T)
}