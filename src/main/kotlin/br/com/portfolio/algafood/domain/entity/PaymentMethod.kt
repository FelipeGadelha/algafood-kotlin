package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "payment_methods")
data class PaymentMethod(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val description: String
) {
    fun update(updated: PaymentMethod) = PaymentMethod(
        id = if (Objects.isNull(id)) this.id else updated.id,
        description = updated.description
    )
}
