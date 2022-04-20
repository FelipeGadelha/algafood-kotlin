package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity @Table(name = "product_image")
class ProductImage(
    @Id @Column(name = "product_id")
    var id: Long,
    val fileName: String,
    val description: String,
    val contentType: String,
    val size: Long,
    @OneToOne(fetch = FetchType.LAZY) @MapsId val product: Product
) {
    fun update(updated: ProductImage) = ProductImage(
        id = if (Objects.isNull(id)) this.id else updated.id,
        fileName = updated.fileName,
        description = updated.description,
        contentType = updated.contentType,
        size = updated.size,
        product = if (Objects.isNull(product)) this.product else updated.product
    )
    fun getRestaurantId(): Long? = product.restaurant.id
}
