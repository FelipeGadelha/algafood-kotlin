package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.request.StateRq
import br.com.portfolio.algafood.api.v1.dto.response.StateRs
import br.com.portfolio.algafood.domain.entity.State
import br.com.portfolio.algafood.domain.service.StateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/states")
class StateController constructor(
    private val stateService: StateService
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll() = ResponseEntity
            .ok(stateService.findAll().map { s -> StateRs(s) })

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity
        .ok(StateRs(stateService.findById(id)))

    @PostMapping
    fun save(@RequestBody @Valid stateRq: StateRq): ResponseEntity<StateRs> {
        val saved: State = stateService.save(stateRq.toEntity())
        return ResponseEntity(StateRs(saved), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid stateRq: StateRq): ResponseEntity<StateRs> {
        val state: State = stateService.update(id, stateRq.toEntity())
        return ResponseEntity.ok(StateRs(state))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { stateService.deleteById(id) }
}