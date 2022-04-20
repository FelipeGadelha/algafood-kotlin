package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.Restaurant

import java.math.BigDecimal

interface IRestaurantRepositoryQueries {
    fun find(name: String, taxInit: BigDecimal, taxFinal: BigDecimal): List<Restaurant>
    fun findWithFreeTax(name: String): List<Restaurant>
}