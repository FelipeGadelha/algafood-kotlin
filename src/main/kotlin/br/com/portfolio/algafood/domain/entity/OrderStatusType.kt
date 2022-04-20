package br.com.portfolio.algafood.domain.entity

enum class OrderStatusType(val description: String) {
    CREATED("Criado"),
    CONFIRMED("Confirmado"),
    DELIVERED("Entregue"),
    CANCELED("Cancelado");
}