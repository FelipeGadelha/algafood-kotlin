package br.com.portfolio.algafood.domain.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "cities")
data class City(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @NotBlank val name: String = "",
    @NotNull
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "state_id", nullable = false)
    val state: State = State()
) {
    fun update(updated: City) = City(
        id = if (Objects.isNull(id)) this.id else updated.id,
        name = updated.name,
        state = updated.state
    )
}