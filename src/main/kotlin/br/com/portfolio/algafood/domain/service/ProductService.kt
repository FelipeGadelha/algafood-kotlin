package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Product
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.repository.IProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ProductService @Autowired constructor(
    private val restaurantService: RestaurantService,
    private val productRepository: IProductRepository
) {

    companion object {
        private const val MSG_RESTAURANT_PRODUCT_NOT_FOUND = "Não existe um cadastro de produto " +
                "com código %d para o restaurante de código %d"
    }
    @Transactional
    fun findAll(restaurantId: Long, includeInactive: Boolean) =
        if(includeInactive) productRepository.findByRestaurantId(restaurantId)
            else productRepository.findByActiveRestaurants(restaurantId)

    @Transactional
    fun findById(restaurantId: Long, id: Long): Product {
        return productRepository.findById(restaurantId, id)
            .orElseThrow { EntityNotFoundException(String
                .format(MSG_RESTAURANT_PRODUCT_NOT_FOUND, id, restaurantId)) }
    }

    @Transactional
    fun save(product: Product, restaurantId: Long): Product {
        val restaurant = restaurantService.findById(restaurantId)
        val saved = product.update(product)
            .copy(restaurant = restaurant)
        return productRepository.save(saved)
    }

    @Transactional
    fun update(updated: Product, restaurantId: Long, id: Long): Product {
        val (_, _, _, _, _, _, products) = restaurantService.findById(restaurantId)
        val product = products
            .stream()
            .filter { p -> p.id == id }
            .findFirst()
            .orElseThrow{ EntityNotFoundException(String
                .format(MSG_RESTAURANT_PRODUCT_NOT_FOUND, id, restaurantId)) }
        val result = product.update(product)
        return productRepository.save(result)
    }
}