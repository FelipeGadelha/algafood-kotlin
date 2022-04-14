package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Kitchen
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.KitchenRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KitchenService(
    private val kitchenRepository: KitchenRepository
) {
    companion object {
        const val MSG_KITCHEN_NOT_FOUND = "Não existe Cozinha com o ID %d"
        const val MSG_KITCHEN_IN_USE = "Cozinha com o %d não pode ser removida, pois esta em uso"
    }

    @Transactional
    fun findAll(): List<Kitchen> = kitchenRepository.findAll()

    @Transactional
    fun findById(id: Long): Kitchen = kitchenRepository
        .findById(id)
        .orElseThrow{ EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id))}

    @Transactional
    fun save(kitchen: Kitchen): Kitchen = kitchenRepository.save(kitchen)

    @Transactional
    fun update(id: Long, updated: Kitchen): Kitchen {
        var kitchen = this.findById(id)
        kitchen = Kitchen(id = kitchen.id, name = updated.name)
        return this.save(kitchen)
    }

    @Transactional
    fun deleteById(id: Long) {
        try {
            kitchenRepository.run {
                deleteById(id)
                flush()
            }
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_KITCHEN_IN_USE, id))
        }
    }
}