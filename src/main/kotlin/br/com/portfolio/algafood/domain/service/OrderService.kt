package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.entity.Order
import br.com.portfolio.algafood.domain.entity.OrderItem
import br.com.portfolio.algafood.domain.exception.BusinessException
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import br.com.portfolio.algafood.domain.filter.OrderFilter
import br.com.portfolio.algafood.domain.repository.IOrderRepository
import br.com.portfolio.algafood.infra.especification.OrderSpecs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService @Autowired constructor(
    private val orderRepository: IOrderRepository,
    private val restaurantService: RestaurantService,
    private val paymentMethodService: PaymentMethodService,
    private val productService: ProductService,
    private val userService: UserService,
    private val cityService: CityService
) {
    companion object {
        private const val MSG_ORDER_NOT_FOUND: String = "Não existe um pedido com código %s";
    }

    @Transactional
    fun findAll(filter: OrderFilter, pageable: Pageable): Page<Order> {
        return orderRepository.findAll(OrderSpecs.useFilter(filter), pageable); }

    @Transactional
    fun findByCode(code: String): Order = orderRepository.findByCode(code)
        .orElseThrow{ throw EntityNotFoundException(String
            .format(MSG_ORDER_NOT_FOUND, code))}

    @Transactional
    fun save(order: Order): Order {
        val restaurantId: Long  = order.restaurant.id!!
        val methodId: Long  = order.method.id!!
        val cityId: Long  = order.addressDelivery.city.id!!
        val clientId: Long = order.client.id!!
        val restaurant = restaurantService.findById(restaurantId)
        val method = paymentMethodService.findById(methodId)
        val city = cityService.findById(cityId)
        val client = userService.findById(clientId)

//        var itens = order.ordersItens.stream()
//            .map(item -> {
//            var product = productService.findById(restaurantId, item.getProduct().getId());
//            return OrderItem.builder()
//                .clone(item)
//                .product(product)
//                .order(order)
//                .unitPrice(product.getPrice())
//                .build();
//        }).toList()

        if (!restaurant.acceptPaymentMethod(method))
            throw BusinessException(String
                .format("Forma de pagamento '%s' não é aceito por esse restaurante.", method.description))

//        var result = Order.builder()
//            .clone(order)
//            .restaurant(restaurant)
//            .client(client)
//            .addressDelivery(a -> Address.builder()
//        .clone(a)
//            .city(city)
//            .build()
//        ).paymentMethod(method)
//        .taxFreight(order.getRestaurant().getTaxFreight())
//            .ordersItens(itens)
//            .addOrderStatus(OrderStatusType.CREATED)
//            .build()
        return order
//        return orderRepository.save(result);
    }

    private fun validateItems(order: Order, restaurantId: Long): List<OrderItem> {
        return order.ordersItens.map{ item ->
            val product = productService.findById(restaurantId, item.product.id!!)
             item.update(item)
                .copy(
                    product = product,
                    order = order,
                    unitPrice = product.price
                )
        }.toList()
    }
}