package br.com.portfolio.algafood.domain.entity

import java.math.BigDecimal

class DailySale(
    var date: String,
    var totalSales: Long,
    var totalIncome: BigDecimal
)
