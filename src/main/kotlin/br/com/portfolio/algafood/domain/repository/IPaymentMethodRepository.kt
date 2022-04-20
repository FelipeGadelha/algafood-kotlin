package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.PaymentMethod
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPaymentMethodRepository: JpaRepository<PaymentMethod, Long> {

}