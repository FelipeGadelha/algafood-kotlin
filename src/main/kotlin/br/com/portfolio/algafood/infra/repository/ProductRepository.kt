package br.com.portfolio.algafood.infra.repository

import br.com.portfolio.algafood.domain.entity.ProductImage
import br.com.portfolio.algafood.domain.repository.IProductRepositoryQueries
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ProductRepository(
    @PersistenceContext private val manager: EntityManager
): IProductRepositoryQueries {

    @Transactional
    override fun save(photo: ProductImage): ProductImage = manager.merge(photo)
    @Transactional
    override fun remove(photo: ProductImage) { manager.remove(photo) }
}