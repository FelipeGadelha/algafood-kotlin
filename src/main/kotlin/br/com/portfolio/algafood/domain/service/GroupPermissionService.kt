package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Permission
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupPermissionService @Autowired constructor(
    private val groupService: GroupService,
    private val permissionService: PermissionService
) {
    @Transactional
    fun findAll(groupId: Long): Set<Permission> {
        return groupService.findById(groupId).permissions
    }

    @Transactional
    fun connect(groupId: Long, id: Long) {
        val group = groupService.findById(groupId)
        val permission = permissionService.findById(id)
//        Group.builder()
//            .clone(group)
//            .addPermissions(permission)
//            .build()
    }

    @Transactional
    fun disconnect(groupId: Long, id: Long) {
        val group = groupService.findById(groupId)
        val permission = permissionService.findById(id)
//        Group.builder()
//            .clone(group)
//            .removePermissions(permission)
//            .build()
    }
}