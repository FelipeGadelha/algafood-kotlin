package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.api.v1.dto.request.ProductRq
import br.com.portfolio.algafood.api.v1.dto.response.ProductRs
import br.com.portfolio.algafood.domain.service.ProductService
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products")
class ProductController @Autowired constructor(
    private val productService: ProductService
) {
    @GetMapping
    @JsonView(View.Basic::class)
    fun findAll(
        @PathVariable restaurantId: Long,
        @RequestParam(required = false) includeInactive: Boolean
    ) = ResponseEntity.ok(productService
            .findAll(restaurantId, includeInactive).map { p -> ProductRs(p) })

    @GetMapping("/{id}")
    fun findById(@PathVariable restaurantId:
                 Long, @PathVariable id: Long): ResponseEntity<ProductRs> {
        val product = productService.findById(restaurantId, id)
        return ResponseEntity.ok(ProductRs(product))
    }

    @PostMapping
    fun save(@PathVariable restaurantId: Long,
             @RequestBody @Valid productRq: ProductRq
    ): ResponseEntity<ProductRs> {
        val product = productService.save(productRq.toEntity(), restaurantId)
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductRs(product))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable restaurantId: Long,
        @PathVariable id: Long,
        @RequestBody @Valid productRq: ProductRq
    ): ResponseEntity<ProductRs> {
        val product = productService.update(productRq.toEntity(), restaurantId, id)
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductRs(product))
    }
}