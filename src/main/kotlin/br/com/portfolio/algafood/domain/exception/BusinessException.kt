package br.com.portfolio.algafood.domain.exception

class BusinessException(message: String?, cause: Throwable? = null):
    RuntimeException(message, cause)