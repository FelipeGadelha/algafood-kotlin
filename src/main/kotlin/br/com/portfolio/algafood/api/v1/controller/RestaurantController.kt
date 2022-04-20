package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.v1.dto.request.RestaurantRq
import br.com.portfolio.algafood.api.v1.dto.response.RestaurantRs
import br.com.portfolio.algafood.domain.entity.Restaurant
import br.com.portfolio.algafood.domain.exception.BusinessException
import br.com.portfolio.algafood.domain.exception.ValidationException
import br.com.portfolio.algafood.domain.service.RestaurantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.SmartValidator
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException
import javax.validation.Valid


@RestController
@RequestMapping("/v1/restaurants")
class RestaurantController @Autowired constructor(
    private val restaurantService: RestaurantService,
    private val smartValidator: SmartValidator
) {
    @GetMapping
    fun findAll() = ResponseEntity
        .ok(restaurantService.findAll().map{ r -> RestaurantRs(r) })

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<RestaurantRs> {
        val restaurant = restaurantService.findById(id)
        return ResponseEntity.ok(RestaurantRs(restaurant))
    }

    @PostMapping
    fun save(@RequestBody @Valid restaurantRq: RestaurantRq): ResponseEntity<RestaurantRs> {
        val saved = restaurantService.save(restaurantRq.toEntity())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(RestaurantRs(saved))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable @Valid id: Long,
        @RequestBody @Valid restaurantRq: RestaurantRq
    ): ResponseEntity<RestaurantRs> {
        val updated = restaurantService.update(id, restaurantRq.toEntity())
        return ResponseEntity.ok(RestaurantRs(updated))
    }

//    @PatchMapping("/{id}")
//    fun patch(
//        @PathVariable id: Long?,
//        @RequestBody fields: Map<String, Any>,
//        request: HttpServletRequest
//    ): ResponseEntity<RestaurantDetailRs> {
//        val restaurant = restaurantService.findById(id!!)
//        merge(fields, restaurant, request)
//        validate(restaurant, "restaurant")
//        val updated = restaurantService.update(id, restaurant)
//        return ResponseEntity.ok<RestaurantDetailRs>(RestaurantDetailRs(updated))
//    }

    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun activate(@PathVariable id: Long) { restaurantService.activate(id) }

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun massActivations(@RequestBody ids: List<Long>) {
        try {
            restaurantService.activate(ids)
        } catch (ex: EntityNotFoundException) {
            throw BusinessException(ex.message, ex)
        }
    }

    @DeleteMapping("/{id}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun inactivate(@PathVariable id: Long) { restaurantService.inactivate(id) }

    @DeleteMapping("/inactivations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun massInactivations(@RequestBody ids: List<Long>) {
        try {
            restaurantService.inactivate(ids)
        } catch (ex: EntityNotFoundException) {
            throw BusinessException(ex.message, ex)
        }
    }

    @PutMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun open(@PathVariable id: Long) { restaurantService.open(id) }

    @PutMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun close(@PathVariable id: Long) { restaurantService.close(id) }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) { restaurantService.deleteById(id) }

    private fun validate(restaurant: Restaurant, objectName: String) {
        val bindingResult = BeanPropertyBindingResult(restaurant, objectName)
        smartValidator.validate(restaurant, bindingResult)
        if (bindingResult.hasErrors()) throw ValidationException(bindingResult)
    }

//    private fun merge(fields: Map<String, Any>, restaurant: Restaurant, request: HttpServletRequest) {
//        val serHttpRequest = ServletServerHttpRequest(request)
//        try {
//            val mapper = ObjectMapper()
//            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true)
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
//            val converted: Restaurant = mapper.convertValue(fields, Restaurant::class.java)
//            fields.forEach { (propName: String?, propValue: Any?) ->
//                val field: Field = ReflectionUtils.findField(
//                    Restaurant::class.java, propName
//                )
//                field.setAccessible(true)
//                val newValue: Any = ReflectionUtils.getField(field, converted)
//                ReflectionUtils.setField(field, restaurant, newValue)
//            }
//        } catch (e: IllegalArgumentException) {
//            val rootCause: Throwable = ExceptionUtils.getRootCause(e)
//            throw HttpMessageNotReadableException(e.message!!, rootCause, serHttpRequest)
//        }
//    }
}