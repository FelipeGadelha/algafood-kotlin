package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.api.v1.dto.request.CityRq
import br.com.portfolio.algafood.api.v1.dto.response.CityRs
import br.com.portfolio.algafood.domain.service.CityService
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/v1/cities"])
class CityController constructor(
    private val cityService: CityService
) {
    @JsonView(View.Basic::class)
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll() = ResponseEntity
        .ok(cityService.findAll().map {c -> CityRs(c) })

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity
        .ok(CityRs(cityService.findById(id)))

    @PostMapping
    fun save(@RequestBody @Valid cityRq: CityRq): ResponseEntity<CityRs> {
        val saved = cityService.save(cityRq.toEntity())
        return ResponseEntity<CityRs>(CityRs(saved), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid cityRq: CityRq): ResponseEntity<CityRs> {
        val city = cityService.update(id, cityRq.toEntity())
        return ResponseEntity.ok(CityRs(city))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { cityService.deleteById(id) }
}