package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import java.util.Optional


@Repository
interface IOrderRepository :
    ICustomJpaRepository<Order, Long>,
    JpaSpecificationExecutor<Order> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
        attributePaths = ["restaurant", "client", "orderStatus"])
    fun findByCode(code: String): Optional<Order>

    @EntityGraph(
        type = EntityGraph.EntityGraphType.FETCH,
        attributePaths = ["method",
            "restaurant.kitchen",
            "client",
            "ordersItens.order",
            "ordersItens.product",
            "orderStatus"]
    )
    override fun findAll(spec: Specification<Order>?, pageable: Pageable): Page<Order>

    @Query("from Order o join fetch o.client join fetch o.restaurant r join fetch r.kitchen")
    override fun findAll(): List<Order> //    @Query(value = """
    //            select
    //            date(os.moment) as date,
    //            count(o.id) as totalSales,
    //            sum(o.total_value) as totalIncome
    //            from orders o
    //            left join order_status os on o.id = os.order_status_id
    //            where os.status = 'DELIVERED'
    //            group by date(os.moment);
    //            """, nativeQuery = true)
    //    List<DailySaleProjection> findDailySale();
}