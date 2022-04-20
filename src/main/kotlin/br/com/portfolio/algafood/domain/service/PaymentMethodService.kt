package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.PaymentMethod
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IPaymentMethodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PaymentMethodService @Autowired constructor(
    private val paymentMethodRepository: IPaymentMethodRepository
) {

    companion object {
        private const val MSG_PAYMENT_METHOD_NOT_FOUND =
            "Não existe Forma de Pagamento com o ID %d"
        private const val MSG_PAYMENT_METHOD_IN_USE =
            "Forma de Pagamento com o %d não pode ser removido, pois esta em uso"
    }

    @Transactional fun findAll(): List<PaymentMethod> = paymentMethodRepository.findAll()

    @Transactional
    fun findById(id: Long): PaymentMethod = paymentMethodRepository
        .findById(id)
        .orElseThrow { EntityNotFoundException(String.format(MSG_PAYMENT_METHOD_NOT_FOUND, id)) }

    @Transactional
    fun save(paymentMethod: PaymentMethod) = paymentMethodRepository.save(paymentMethod)

    @Transactional
    fun update(id: Long, updated: PaymentMethod): PaymentMethod {
        var paymentMethod = findById(id)
        paymentMethod = paymentMethod.update(paymentMethod)
        return save(paymentMethod)
    }

    @Transactional
    fun deleteById(id: Long) {
        try {
            paymentMethodRepository.run { deleteById(id); flush() }
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_PAYMENT_METHOD_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_PAYMENT_METHOD_IN_USE, id))
        }
    }
}