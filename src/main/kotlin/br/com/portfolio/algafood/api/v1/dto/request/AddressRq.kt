package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.Address
import br.com.portfolio.algafood.domain.entity.City
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AddressRq(
    @field:NotBlank val cep: String,
    @field:NotBlank val place: String,
    @field:NotBlank val number: String,
    val complement: String?,
    @field:NotBlank val district: String,
    @field:NotNull val cityId: Long
) {
    fun toEntity(): Address {
        return Address(
            cep = cep,
            place = place,
            number = number,
            complement = complement,
            district = district,
            city = City(cityId)
        )
    }
}