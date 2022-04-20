package br.com.portfolio.algafood.infra.repository

import br.com.portfolio.algafood.domain.entity.Restaurant
import br.com.portfolio.algafood.domain.repository.IRestaurantRepository
import br.com.portfolio.algafood.domain.repository.IRestaurantRepositoryQueries
import br.com.portfolio.algafood.infra.especification.RestaurantSpecs.Companion.freeFreight
import br.com.portfolio.algafood.infra.especification.RestaurantSpecs.Companion.similarName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import java.math.BigDecimal
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class RestaurantRepository(
    @PersistenceContext private val manager: EntityManager,
    @Autowired @Lazy
    private val iRestaurantRepository: IRestaurantRepository
) : IRestaurantRepositoryQueries {

    override fun find(name: String, taxInit: BigDecimal, taxFinal: BigDecimal): List<Restaurant> {
        val builder = manager.criteriaBuilder
        val criteria: CriteriaQuery<Restaurant> = builder.createQuery(Restaurant::class.java)
        val root: Root<Restaurant> = criteria.from(Restaurant::class.java)
        val predicates: ArrayList<Predicate> = ArrayList()
        if (StringUtils.hasLength(name)) predicates
            .add(builder.like(root.get("name"), "%$name%"))
        if (Objects.nonNull(taxInit)) predicates
            .add(builder.greaterThanOrEqualTo(root.get("taxFreight"), taxInit))
        if (Objects.nonNull(taxFinal)) predicates
            .add(builder.lessThanOrEqualTo(root.get("taxFreight"), taxFinal))
        criteria.where(*predicates.toTypedArray())
        val query = manager.createQuery(criteria)
        return query.resultList
    }

    override fun findWithFreeTax(name: String): List<Restaurant> {
        return iRestaurantRepository.findAll(freeFreight().and(similarName(name)))
    }
}