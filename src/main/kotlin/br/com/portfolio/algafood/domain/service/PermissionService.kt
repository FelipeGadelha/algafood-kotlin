package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Permission
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IPermissionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PermissionService @Autowired constructor(
    private val permissionRepository: IPermissionRepository
) {
    companion object {
        private const val MSG_PERMISSION_NOT_FOUND =
            "Não existe um cadastro de permissão com código %d"
        private const val MSG_PERMISSION_IN_USE =
            "Permissão com o ID %d não pode ser removido, pois esta em uso"
    }
    @Transactional
    fun findAll(): List<Permission> = permissionRepository.findAll()

    @Transactional
    fun findById(id: Long) = permissionRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException(String.format(MSG_PERMISSION_NOT_FOUND, id)) }

    @Transactional
    fun save(permission: Permission) = permissionRepository.save(permission)

    @Transactional
    fun update(id: Long, updated: Permission): Permission {
        var permission: Permission = findById(id)
        permission = permission.update(permission)
        return permissionRepository.save(permission)
    }

    @Transactional
    fun deleteById(id: Long) {
        try {
            permissionRepository.run { deleteById(id); flush() }
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_PERMISSION_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_PERMISSION_IN_USE, id))
        }
    }
}