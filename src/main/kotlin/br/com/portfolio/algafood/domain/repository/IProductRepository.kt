package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.Product
import br.com.portfolio.algafood.domain.entity.ProductImage
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IProductRepository:
    ICustomJpaRepository<Product, Long>,
    IProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurantId and id = :product")
    fun findById(@Param("restaurantId") restaurantId: Long,
                 @Param("product") id: Long): Optional<Product>

    @Query("from Product p where p.active = true and p.restaurant.id = :restaurantId")
    fun findByActiveRestaurants(restaurantId: Long): List<Product>

    fun findByRestaurantId(restaurantId: Long): List<Product>

    @Query(
        "select pi from ProductImage pi join pi.product p " +
                "where p.restaurant.id = :restaurantId and pi.product.id = :productId"
    )
    fun findPhotoById(restaurantId: Long, productId: Long): Optional<ProductImage>
}