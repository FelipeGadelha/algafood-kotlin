package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.Restaurant
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.Optional

@Repository
interface IRestaurantRepository: ICustomJpaRepository<Restaurant, Long>,
    IRestaurantRepositoryQueries,
    JpaSpecificationExecutor<Restaurant> {

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "JOIN FETCH r.kitchen LEFT " +
            "JOIN FETCH r.address.city c " +
            "LEFT JOIN FETCH c.state " +
            "LEFT JOIN FETCH r.paymentMethod")
    override fun findAll(): List<Restaurant>

    fun findByTaxFreightBetween(taxInit: BigDecimal, taxFinal: BigDecimal): List<Restaurant>

    //	@Query("from Restaurant where name like %:name% and kitchen.id = :id")
    fun findByName(name: String, @Param("id") kitchenId: Long): List<Restaurant>

//	List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);

    //	List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);
    fun findFirstRestaurantByNameContaining(name: String?): Optional<Restaurant>

    fun findTop2ByNameContaining(name: String): List<Restaurant>

    fun existsByName(name: String): Boolean

    fun countByKitchenId(kitchen: Long): Int
}