package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.request.KitchenRq
import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs
import br.com.portfolio.algafood.domain.service.KitchenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/kitchens")
class KitchenController(
    private val kitchenService: KitchenService
) {
    @GetMapping
    //@JsonView(View.Basic::class)
    fun findAll() = ResponseEntity
        .ok(kitchenService.findAll().map{ k -> KitchenRs(k) })

    @GetMapping("/{kitchenId}")
    fun findById(@PathVariable("kitchenId") id: Long) = ResponseEntity
            .ok(KitchenRs(kitchenService.findById(id)))

    @PostMapping
    fun save(@RequestBody @Valid kitchenRq: KitchenRq): ResponseEntity<KitchenRs> {
        val kitchen = kitchenService.save(kitchenRq.toEntity())
        return ResponseEntity(KitchenRs(kitchen), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody @Valid kitchenRq: KitchenRq
    ): ResponseEntity<KitchenRs> {
        val kitchen = kitchenService.update(id, kitchenRq.toEntity())
        return ResponseEntity.ok(KitchenRs(kitchen))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = kitchenService.deleteById(id)
}