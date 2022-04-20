package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.api.v1.dto.request.PermissionRq
import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs
import br.com.portfolio.algafood.domain.service.PermissionService
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/v1/permissions")
class PermissionController @Autowired constructor(
    private val permissionService: PermissionService
) {
    @GetMapping
    @JsonView(View.Basic::class)
    fun findAll() = ResponseEntity.ok(
            permissionService.findAll().map {p -> PermissionRs(p) })

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<PermissionRs> {
        val permission = permissionService.findById(id)
        return ResponseEntity.ok(PermissionRs(permission))
    }

    @PostMapping
    fun save(@RequestBody @Valid permissionRq: PermissionRq): ResponseEntity<PermissionRs> {
        val permission = permissionService.save(permissionRq.toEntity())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(PermissionRs(permission))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody permissionRq: PermissionRq): ResponseEntity<PermissionRs> {
        val permission = permissionService.update(id, permissionRq.toEntity())
        return ResponseEntity.ok(PermissionRs(permission))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { permissionService.deleteById(id) }
}