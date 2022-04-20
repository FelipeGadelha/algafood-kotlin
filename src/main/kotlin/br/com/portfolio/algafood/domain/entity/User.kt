package br.com.portfolio.algafood.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import kotlin.collections.ArrayList

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @NotBlank val name: String = "",
    @NotBlank val email: String = "",
    @NotBlank val password: String = "",

    @ManyToMany
    @JoinTable(
        name = "users_groups",
        joinColumns = [JoinColumn(name = "users_id")],
        inverseJoinColumns = [JoinColumn(name = "groups_id")]
    )
    val groups: List<Group> = ArrayList(),

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @UpdateTimestamp @Column(name = "update_date", nullable = false)
    val updateDate: OffsetDateTime = OffsetDateTime.now()
) {
    fun update(updated: User) = User(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name,
        email = updated.email,
        password = if (Objects.isNull(password)) this.password else updated.password,
        groups = if (Objects.isNull(groups)) this.groups else updated.groups,
        creationDate = if (Objects.isNull(creationDate)) this.creationDate else updated.creationDate,
        updateDate = if (Objects.isNull(updateDate)) this.updateDate else updated.updateDate
    )
}
