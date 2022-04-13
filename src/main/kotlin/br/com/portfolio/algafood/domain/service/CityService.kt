package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.City
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.CityRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityService(
    private val cityRepository: CityRepository,
    private val stateService: StateService
) {
    companion object {
        const val MSG_CITY_NOT_FOUND = "Não existe Cidade com o ID %d"
        const val MSG_CITY_IN_USE = "Cidade com o %d não pode ser removida, pois esta em uso"
    }

    @Transactional
    fun findAll(): List<City> = cityRepository.findAll()

    @Transactional
    fun findById(id: Long): City = cityRepository
        .findById(id)
        .orElseThrow{ EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id))}

    @Transactional
    fun save(city: City): City {
        val stateId = city.state.id
        val state = stateService.findById(stateId!!)
        val saved = city.copy(state = state)
        return cityRepository.save(saved)
    }

    @Transactional
    fun update(id: Long, updated: City): City {
        var city = this.findById(id)
        city = city.copy(name = updated.name)
        return this.save(city)
    }

    @Transactional
    fun deleteById(id: Long) {
        try {
            cityRepository.run {
                deleteById(id)
                flush()
            }
        } catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_CITY_IN_USE, id))
        }
    }
}