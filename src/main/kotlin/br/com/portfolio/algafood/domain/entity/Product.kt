package br.com.portfolio.algafood.domain.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val active: Boolean,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "restaurant_id", nullable = false)
    val restaurant: Restaurant = Restaurant()
) {
    fun update(updated: Product) = Product(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name,
        description = updated.description,
        price = updated.price,
        active = updated.active,
        restaurant = if (Objects.isNull(restaurant)) this.restaurant else updated.restaurant
    )
}