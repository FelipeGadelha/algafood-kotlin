package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Group
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class GroupService @Autowired constructor(
    private val groupRepository: IGroupRepository
) {

    companion object {
        private const val MSG_GROUP_NOT_FOUND = "Não existe Grupo com o ID %d"
        private const val MSG_GROUP_IN_USE =
            "Grupo com o ID %d não pode ser removido, pois esta em uso"
    }
    @Transactional fun findAll(): List<Group> = groupRepository.findAll()

    @Transactional
    fun findById(id: Long): Group = groupRepository.findById(id)
        .orElseThrow { EntityNotFoundException(String.format(MSG_GROUP_NOT_FOUND, id)) }

    @Transactional fun save(group: Group): Group = groupRepository.save(group)

    @Transactional
    fun update(id: Long, update: Group): Group {
        var group: Group = findById(id)
        group = group.update(update)
        return groupRepository.save(group)
    }

    @Transactional
    fun deleteById(id: Long) {
        try { groupRepository.run { deleteById(id); flush() } }
        catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_GROUP_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_GROUP_IN_USE, id))
        }
    }
}