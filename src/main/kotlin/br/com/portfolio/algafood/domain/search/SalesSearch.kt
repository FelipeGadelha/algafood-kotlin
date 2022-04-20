package br.com.portfolio.algafood.domain.search

import br.com.portfolio.algafood.domain.entity.DailySale
import br.com.portfolio.algafood.domain.filter.DailySaleFilter


interface SalesSearch {
    fun findDailySale(filter: DailySaleFilter, offset: String): List<DailySale>
}