package br.com.portfolio.algafood.infra.especification

import br.com.portfolio.algafood.domain.entity.Restaurant
import org.springframework.data.jpa.domain.Specification
import java.math.BigDecimal
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root


class RestaurantSpecs {
    companion object {
        fun freeFreight(): Specification<Restaurant> {
            return Specification { root, _, builder ->
                builder.equal(root.get<Any>("taxFreight"), BigDecimal.ZERO)
            }
        }

        fun similarName(name: String): Specification<Restaurant> {
            return Specification { root: Root<Restaurant>, query: CriteriaQuery<*>, builder: CriteriaBuilder ->
                builder.like(root.get("name"), "%$name%")
            }
        }
    }
}