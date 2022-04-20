package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Restaurant
import br.com.portfolio.algafood.domain.exception.EntityInUseException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IRestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer


@Service
class RestaurantService @Autowired constructor(
    private val restaurantRepository: IRestaurantRepository,
    private val kitchenService: KitchenService,
    private val cityService: CityService
) {
    companion object {
        private const val MSG_RESTAURANT_NOT_FOUND = "Não existe Restaurante com o ID %d"
        private const val MSG_RESTAURANT_IN_USE = "Restaurante com o %d não pode ser removido, pois esta em uso"
    }

    @Transactional
    fun findAll(): List<Restaurant> = restaurantRepository.findAll()

    @Transactional
    fun findById(id: Long): Restaurant {
        return restaurantRepository.findById(id)
            .orElseThrow { EntityNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, id)) }
    }

    @Transactional
    fun save(restaurant: Restaurant): Restaurant {
        val kitchenId: Long? = restaurant.kitchen.id
        val kitchen = kitchenService.findById(kitchenId!!)
        val city = cityService.findById(restaurant.getCityId()!!)
        val saved = restaurant.copy(
            kitchen = kitchen,
            address = restaurant.address.copy(city = city)
        )
        return restaurantRepository.save(saved)
    }

    @Transactional
    fun update(id: Long, updated: Restaurant): Restaurant {
        var restaurant = findById(id)
        restaurant = restaurant.update(updated)
        return save(restaurant)
    }

    fun patch(restaurant: Restaurant?): Restaurant? {
        println(restaurant)
        return null
    }
    @Transactional fun activate(id: Long) { findById(id).activate() }
    @Transactional fun activate(ids: List<Long>)
        { ids.forEach(Consumer { id: Long -> this.activate(id) }) }
    @Transactional fun inactivate(id: Long) { findById(id).inactivate() }
    @Transactional fun inactivate(ids: List<Long>)
        { ids.forEach(Consumer { id: Long -> this.inactivate(id) }) }
    @Transactional fun open(id: Long) { findById(id) }
    @Transactional fun close(id: Long) { findById(id) }

    @Transactional
    fun deleteById(id: Long) {
        try { restaurantRepository.run { deleteById(id); flush() } }
        catch (e: EmptyResultDataAccessException) {
            throw EntityNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, id))
        } catch (e: DataIntegrityViolationException) {
            throw EntityInUseException(String.format(MSG_RESTAURANT_IN_USE, id))
        }
    }
}