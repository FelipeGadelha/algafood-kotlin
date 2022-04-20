package br.com.portfolio.algafood.domain.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.math.BigDecimal
import java.util.*
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.Table
import kotlin.collections.ArrayList

@Entity @Table(name = "orders")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val code: String,
    val subtotal: BigDecimal,
    val taxFreight: BigDecimal,
    val totalValue: BigDecimal,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "payment_method_id", nullable = false)
    val method: PaymentMethod,
    @ManyToOne @JoinColumn(name = "restaurant_id", nullable = false)
    val restaurant: Restaurant,
    @ManyToOne @JoinColumn(name = "user_client_id", nullable = false)
    val client: User,
    @Embedded val addressDelivery: Address,
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    val ordersItens: List<OrderItem> = ArrayList(),
    @ElementCollection
    @CollectionTable(name = "order_status",
        joinColumns = [JoinColumn(name = "order_status_id")])
    @OrderBy("moment asc")
    val orderStatus: SortedSet<OrderStatus> = TreeSet()
) {
    fun update(updated: Order) = Order(
        id = if (Objects.isNull(id)) this.id else updated.id,
        code = if (Objects.isNull(code)) this.code else updated.code,
        subtotal = updated.subtotal,
        taxFreight = updated.taxFreight,
        totalValue = updated.totalValue,
        method = updated.method,
        restaurant = updated.restaurant,
        client = updated.client,
        addressDelivery = updated.addressDelivery,
        ordersItens = updated.ordersItens,
        orderStatus = updated.orderStatus
    )
}
