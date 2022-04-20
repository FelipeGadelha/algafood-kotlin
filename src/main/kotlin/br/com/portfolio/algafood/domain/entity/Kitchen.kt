package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "kitchens")
data class Kitchen (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @NotBlank val name: String = ""
) {
    fun update(updated: Kitchen) = Kitchen(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name
    )
}