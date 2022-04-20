package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.request.GroupRq
import br.com.portfolio.algafood.api.v1.dto.response.GroupRs
import br.com.portfolio.algafood.domain.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/v1/groups")
class GroupController @Autowired constructor(
    private val groupService: GroupService
) {
    @GetMapping
    fun findAll(): ResponseEntity<List<GroupRs>> = ResponseEntity
        .ok(groupService.findAll().map { g -> GroupRs(g) })


    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<GroupRs> = ResponseEntity
        .ok(GroupRs(groupService.findById(id)))

    @PostMapping
    fun save(@RequestBody groupRq: GroupRq): ResponseEntity<GroupRs> {
        val group = groupService.save(groupRq.toEntity())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(GroupRs(group))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid groupRq: GroupRq): ResponseEntity<GroupRs> {
        val group = groupService.update(id, groupRq.toEntity())
        return ResponseEntity.ok(GroupRs(group))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { groupService.deleteById(id) }
}