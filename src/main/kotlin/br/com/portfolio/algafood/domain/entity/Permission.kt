package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.*

@Entity @Table(name = "permissions")
class Permission(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val description: String
) {
    fun update(updated: Permission) = Permission(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name,
        description = updated.name
    )
}