package br.com.portfolio.algafood.infra.repository

import br.com.portfolio.algafood.domain.repository.ICustomJpaRepository
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class CustomJpaRepository<T : Any, ID>(
    entityInformation: JpaEntityInformation<T, *>,
    @PersistenceContext private val manager: EntityManager
): SimpleJpaRepository<T, ID>(entityInformation, manager),
    ICustomJpaRepository<T, ID> {

    override fun findFirstRegister(): Optional<T> {
        val jpql = "from " + domainClass.name
        val entity = manager.createQuery(jpql, domainClass)
            .setMaxResults(1)
            .singleResult
        return Optional.ofNullable(entity)
    }

    override fun detach(entity: T) {
        manager.detach(entity)
    }
}