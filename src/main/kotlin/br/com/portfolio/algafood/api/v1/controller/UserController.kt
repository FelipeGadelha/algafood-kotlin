package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.View
import br.com.portfolio.algafood.api.v1.dto.request.UserRq
import br.com.portfolio.algafood.api.v1.dto.request.UserUpdateRq
import br.com.portfolio.algafood.api.v1.dto.response.UserRs
import br.com.portfolio.algafood.domain.service.UserService
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/v1/users")
class UserController @Autowired constructor(
    private val userService: UserService
) {
    @GetMapping @JsonView(View.Basic::class)
    fun findAll() = ResponseEntity.ok(
            userService.findAll().map { u -> UserRs(u) })

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity
        .ok(UserRs(userService.findById(id)))

    @PostMapping
    fun save(@Valid @RequestBody userRq: UserRq): ResponseEntity<UserRs> {
        val saved = userService.save(userRq.toEntity())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UserRs(saved))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody userUpdateRq: UserUpdateRq):
            ResponseEntity<UserRs> {
        val update = userService.update(id, userUpdateRq.toEntity())
        return ResponseEntity.ok(UserRs(update))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { userService.deleteById(id) }
}