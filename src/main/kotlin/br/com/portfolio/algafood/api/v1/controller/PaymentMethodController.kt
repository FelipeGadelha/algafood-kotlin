package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.request.PaymentMethodRq
import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs
import br.com.portfolio.algafood.domain.service.PaymentMethodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/v1/payment-methods")
class PaymentMethodController @Autowired constructor(
    private val paymentMethodService: PaymentMethodService
) {
    @GetMapping
    fun findAll() = ResponseEntity
        .ok(paymentMethodService.findAll().map { p -> PaymentMethodRs(p) })


    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity
        .ok(PaymentMethodRs(paymentMethodService.findById(id)))

    @PostMapping
    fun save(@RequestBody @Valid paymentMethodRq: PaymentMethodRq): ResponseEntity<PaymentMethodRs> {
        val saved = paymentMethodService.save(paymentMethodRq.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(PaymentMethodRs(saved))
    }

    @PutMapping
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid paymentMethodRq: PaymentMethodRq
    ): ResponseEntity<PaymentMethodRs> {
        val saved = paymentMethodService.update(id, paymentMethodRq.toEntity())
        return ResponseEntity.ok(PaymentMethodRs(saved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { paymentMethodService.deleteById(id) }
}