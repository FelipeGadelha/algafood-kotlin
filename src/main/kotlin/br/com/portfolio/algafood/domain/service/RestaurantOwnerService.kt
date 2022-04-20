package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RestaurantOwnerService @Autowired constructor(
    private val restaurantService: RestaurantService,
    private val userService: UserService
) {
    @Transactional
    fun findAllOwner(restaurantId: Long): Set<User> {
        return restaurantService.findById(restaurantId).owners
    }

    @Transactional
    fun connectOwner(id: Long, userId: Long) {
        val restaurant = restaurantService.findById(id)
        val owner = userService.findById(userId)
//        Restaurant.builder()
//            .clone(restaurant)
//            .addOwner(owner)
//            .build()
    }

    @Transactional
    fun disconnectOwner(id: Long, userId: Long) {
        val restaurant = restaurantService.findById(id)
        val owner = userService.findById(userId)
//        Restaurant.builder()
//            .clone(restaurant)
//            .removeOwner(owner)
//            .build()
    }
}