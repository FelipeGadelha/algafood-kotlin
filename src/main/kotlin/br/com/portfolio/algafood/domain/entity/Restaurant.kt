package br.com.portfolio.algafood.domain.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.Objects
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@NamedEntityGraph(
    name = "order_with_all_associations",
    includeAllAttributes = true
)
@Entity @Table(name = "restaurants")
data class Restaurant(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @NotNull @NotEmpty @NotBlank
    @Column(nullable = false)
    val name: String = "",

//	@DecimalMin("1")
//	@PositiveOrZero
//    @TaxFreight
    @Column(name = "tax_freight", nullable = false)
    val taxFreight: BigDecimal = BigDecimal.ZERO,

    @Embedded val address: Address = Address(),

    @NotNull @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.ALL])
    @JoinColumn(name = "kitchen_id", nullable = false)
    @JsonBackReference
    val kitchen: Kitchen = Kitchen(),

    @ManyToMany
    @JoinTable(
        name = "restaurants_payment_methods",
        joinColumns = [JoinColumn(name = "restaurant_id")],
        inverseJoinColumns = [JoinColumn(name = "payment_method_id")]
    )
    val paymentMethod: Set<PaymentMethod> = HashSet(),

    @OneToMany(mappedBy = "restaurant") @JsonManagedReference
    val products: List<Product> = ArrayList(),

    var active: Boolean = true,
    var open: Boolean = true,

    @ManyToMany
    @JoinTable(
        name = "users_restaurants_owner",
        joinColumns = [JoinColumn(name = "restaurant_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val owners: Set<User> = HashSet(),

    @CreationTimestamp @Column(name = "creation_date", nullable = false)
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @UpdateTimestamp @Column(name = "update_date", nullable = false)
    val updateDate: OffsetDateTime = OffsetDateTime.now()
) {
    fun getCityId(): Long? = this.address.city.id
    fun acceptPaymentMethod(method: PaymentMethod?): Boolean = paymentMethod.contains(method)
    fun activate() { active = true }
    fun inactivate() { active = false }
    fun open() { open = true }
    fun close() { open = false }
    fun addPaymentMethod(method: PaymentMethod) = this.copy(paymentMethod = paymentMethod + method)
    fun removePaymentMethod(method: PaymentMethod) = this.copy(paymentMethod = paymentMethod - method)

    fun update(updated: Restaurant) = Restaurant(
            id = if (Objects.isNull(id)) this.id else updated.id,
            name = updated.name,
            taxFreight = updated.taxFreight,
            address = updated.address,
            kitchen = updated.kitchen,
            paymentMethod = if (Objects.isNull(paymentMethod)) this.paymentMethod else updated.paymentMethod,
            products = if (Objects.isNull(products)) this.products else updated.products,
            active = if (Objects.isNull(active)) this.active else updated.active,
            open = if (Objects.isNull(open)) this.open else updated.open,
            owners = if (Objects.isNull(owners)) this.owners else updated.owners,
            creationDate = if (Objects.isNull(creationDate)) this.creationDate else updated.creationDate,
            updateDate = if (Objects.isNull(updateDate)) this.updateDate else updated.updateDate
        )

}