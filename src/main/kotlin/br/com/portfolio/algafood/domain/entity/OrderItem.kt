package br.com.portfolio.algafood.domain.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val totalPrice: BigDecimal,
    val observation: String,
    @ManyToOne @JoinColumn(nullable = false) @JsonBackReference	val order: Order,
    @ManyToOne @JoinColumn(nullable = false) val product: Product
) {

    fun update(updated: OrderItem) = OrderItem(
        id = if (Objects.isNull(id)) this.id else updated.id,
        quantity = updated.quantity,
        unitPrice = updated.unitPrice,
        totalPrice = updated.totalPrice,
        observation = updated.observation,
        order = updated.order,
        product = updated.product
    )
}
