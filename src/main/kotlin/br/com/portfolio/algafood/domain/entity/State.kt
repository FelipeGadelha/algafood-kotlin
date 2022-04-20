package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "states")
data class State(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @field:NotBlank val name: String = ""
) {
    fun update(updated: State) = State(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name
    )
}