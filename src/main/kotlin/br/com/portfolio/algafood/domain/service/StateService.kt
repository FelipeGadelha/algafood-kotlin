package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.State
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IStateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StateService @Autowired constructor(
    private val stateRepository: IStateRepository
) {
    companion object {
        const val MSG_STATE_NOT_FOUND = "Não existe Estado com o ID %d"
        const val MSG_STATE_IN_USE = "Estado com o %d não pode ser removido, pois esta em uso"
    }

    @Transactional
    fun findAll(): List<State> = stateRepository.findAll()

    @Transactional
    fun findById(id: Long): State = stateRepository
        .findById(id)
        .orElseThrow{ EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id))}

    @Transactional
    fun save(state: State): State = stateRepository.save(state)

    @Transactional
    fun update(id: Long, updated: State): State {
        var state = this.findById(id)
        state = state.update(updated)
        return this.save(state)
    }

    @Transactional
    fun deleteById(id: Long) {
        try { stateRepository.run { deleteById(id); flush() } }
        catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_STATE_IN_USE, id))
        }
    }
}