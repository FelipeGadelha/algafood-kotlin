package br.com.portfolio.algafood.domain.entity

import javax.persistence.*

@Entity
@Table(name = "states")
data class State(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String
)