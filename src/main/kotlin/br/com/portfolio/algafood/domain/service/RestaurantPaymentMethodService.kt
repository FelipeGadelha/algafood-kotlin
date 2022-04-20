package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.PaymentMethod
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class RestaurantPaymentMethodService @Autowired constructor(
    private val restaurantService: RestaurantService,
    private val paymentMethodService: PaymentMethodService
) {
    @Transactional
    fun findAll(restaurantId: Long): Set<PaymentMethod> {
        return restaurantService.findById(restaurantId).paymentMethod
    }

    @Transactional
    fun deletePaymentMethod(restaurantId: Long, paymentMethodId: Long) {
        val restaurant = restaurantService.findById(restaurantId)
        val paymentMethod = paymentMethodService.findById(paymentMethodId)
        restaurant.removePaymentMethod(paymentMethod)
//        val methods = restaurant.paymentMethod
//        var set = HashSet<PaymentMethod>()
//        methods.forEach { p -> set.add(p) }
//        restaurant.copy(paymentMethod = set)
    }

    @Transactional
    fun addPaymentMethod(restaurantId: Long, paymentMethodId: Long) {
        val restaurant = restaurantService.findById(restaurantId)
        val paymentMethod = paymentMethodService.findById(paymentMethodId)
        restaurant.addPaymentMethod(paymentMethod)
    }
}
