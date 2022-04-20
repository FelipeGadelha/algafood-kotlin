package br.com.portfolio.algafood.infra.especification

import br.com.portfolio.algafood.domain.entity.Order
import br.com.portfolio.algafood.domain.filter.OrderFilter
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.*


class OrderSpecs {
    companion object {
        fun useFilter(filter: OrderFilter): Specification<Order> {
            return Specification<Order> { root, _, builder ->
                val predicates: ArrayList<Predicate> = ArrayList()
                if (filter.clientId != null) predicates
                    .add(builder.equal(root.get<Any>("client"), filter.clientId))
                if (filter.restaurantId != null) predicates
                    .add(builder.equal(root.get<Any>("restaurant"), filter.restaurantId))
                if (filter.status != null) predicates
                    .add((builder.equal(root
                        .join<Any, Any>("orderStatus", JoinType.LEFT)
                        .get<Any>("status"), filter.status)))

                if (filter.creationDate != null) predicates
                    .add(
                        builder.greaterThanOrEqualTo(
                            root.join<Any, Any>("orderStatus", JoinType.LEFT) //.<Order>get("orderStatus")
                                .get("moment"), filter.creationDate))
                if (filter.finishDate != null) predicates
                    .add(
                        builder.lessThanOrEqualTo(
                            root.join<Any, Any>("orderStatus", JoinType.LEFT)
                                .get("moment"), filter.finishDate))
                builder.and(*predicates.toTypedArray())
            }
        }
    }
}