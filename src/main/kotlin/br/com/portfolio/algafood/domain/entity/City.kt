package br.com.portfolio.algafood.domain.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "cities")
data class City private constructor(
    @field:NotBlank val name: String,

    @field:NotNull
    @field:ManyToOne(cascade = [CascadeType.ALL])
    @field:JoinColumn(name = "state_id", nullable = false)
    val state: State
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    constructor(builder: Builder):
        this(
            name = builder.name,
            state = builder.state
        )

    class Builder {
        private var id: Long? = null
        lateinit var name: String
        lateinit var state: State

        fun id(id: Long) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun name(state: State) = apply { this.state = state }
        fun build() = City(this)
    }
}