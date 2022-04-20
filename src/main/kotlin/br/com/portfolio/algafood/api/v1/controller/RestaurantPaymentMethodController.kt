package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs
import br.com.portfolio.algafood.domain.entity.PaymentMethod
import br.com.portfolio.algafood.domain.service.RestaurantPaymentMethodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/payment-methods")
class RestaurantPaymentMethodController @Autowired constructor(
    private val restaurantPaymentMethodService: RestaurantPaymentMethodService
) {
    @GetMapping
    fun findAll(@PathVariable restaurantId: Long): ResponseEntity<List<PaymentMethodRs>> {
        return ResponseEntity
            .ok(restaurantPaymentMethodService
                .findAll(restaurantId).map { p -> PaymentMethodRs(p) })
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePaymentMethod(@PathVariable restaurantId: Long, @PathVariable id: Long) {
        restaurantPaymentMethodService.deletePaymentMethod(restaurantId, id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun addPaymentMethod(@PathVariable restaurantId: Long, @PathVariable id: Long) {
        restaurantPaymentMethodService.addPaymentMethod(restaurantId, id)
    }
}