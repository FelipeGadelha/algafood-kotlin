package br.com.portfolio.algafood.domain.entity

import javax.persistence.*

@Entity
@Table(name = "states")
class State(
    val name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    constructor(builder: Builder):
        this(
            name = builder.name,
        )

    class Builder {
        private var id: Long? = null
        lateinit var name: String
        fun id(id: Long) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun build() = State(this)
    }
}