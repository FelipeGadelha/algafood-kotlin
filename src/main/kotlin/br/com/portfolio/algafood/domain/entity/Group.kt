package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
@Table(name = "groups")
class Group(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    @ManyToMany
    @JoinTable(
        name = "groups_permission",
        joinColumns = [JoinColumn(name = "groups_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    val permissions: Set<Permission> = HashSet()
) {
    fun update(updated: Group) = Group(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name,
        permissions = if (Objects.isNull(permissions)) this.permissions else updated.permissions
    )
}