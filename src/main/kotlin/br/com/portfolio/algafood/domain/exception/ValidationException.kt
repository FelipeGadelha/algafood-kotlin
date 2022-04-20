package br.com.portfolio.algafood.domain.exception

import org.springframework.validation.BindingResult

class ValidationException(val bindingResult: BindingResult):
    RuntimeException()
