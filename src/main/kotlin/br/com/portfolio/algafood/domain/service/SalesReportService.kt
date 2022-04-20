package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.filter.DailySaleFilter


interface SalesReportService {
    fun emitDailySales(filter: DailySaleFilter, offset: String): ByteArray
}