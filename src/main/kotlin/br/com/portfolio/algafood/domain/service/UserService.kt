package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.User
import br.com.portfolio.algafood.domain.exception.BusinessException
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class UserService @Autowired constructor(
    private val userRepository: IUserRepository
) {

    companion object {
        private const val MSG_USER_NOT_FOUND = "Não existe Usuário com o ID %d"
        private const val MSG_USER_IN_USER =
            "Usuário com o ID %d não pode ser removido, pois esta em uso"
        private const val MSG_USER_ALREADY_EXISTS =
            "Já existe um usuário cadastrado com o e-mail %s"
    }

    @Transactional
    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    fun findById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { EntityNotFoundException(String.format(MSG_USER_NOT_FOUND, id)) }
    }

    @Transactional
    fun save(user: User): User {
        userRepository.detach(user)
        val optional: Optional<User> = userRepository.findByEmail(user.email)
        if (optional.isPresent && optional.get() != user)
            throw BusinessException(String.format(MSG_USER_ALREADY_EXISTS, user.email))
        return userRepository.save(user)
    }

    @Transactional
    fun update(id: Long, updated: User): User {
        var user: User = findById(id)
        user = user.update(updated)
        return save(user)
    }

    @Transactional
    fun deleteById(id: Long) {
        try {
            userRepository.run { deleteById(id); flush() }
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_USER_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_USER_IN_USER, id))
        }
    }
}