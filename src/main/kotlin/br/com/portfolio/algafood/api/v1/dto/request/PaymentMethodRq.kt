package br.com.portfolio.algafood.api.v1.dto.request

import br.com.portfolio.algafood.domain.entity.PaymentMethod
import javax.validation.constraints.NotBlank

class PaymentMethodRq(
    @field:NotBlank val description: String
) {
    fun toEntity() = PaymentMethod(description = description)
}
