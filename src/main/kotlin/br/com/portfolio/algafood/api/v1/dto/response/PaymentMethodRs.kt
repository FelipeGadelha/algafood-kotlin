package br.com.portfolio.algafood.api.v1.dto.response

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.domain.entity.PaymentMethod
import com.fasterxml.jackson.annotation.JsonView

class PaymentMethodRs private constructor(
    @JsonView(View.Basic::class) val id: Long,
    @JsonView(View.Basic::class) val description: String
) {
    constructor(paymentMethod: PaymentMethod) :
        this(
            id = paymentMethod.id!!,
            description = paymentMethod.description
        )
}