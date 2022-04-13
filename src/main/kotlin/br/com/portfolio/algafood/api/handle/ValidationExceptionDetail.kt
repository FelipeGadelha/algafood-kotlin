package br.com.portfolio.algafood.api.handle

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.OffsetDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class ValidationExceptionDetail internal constructor(builder: Builder<*>):
    ExceptionDetail(builder) {
    val errors: Map<String?, Set<String?>>

    init { this.errors = builder.errors }

    private class ValidationBuilder : Builder<ValidationBuilder>() {
        override fun self(): ValidationBuilder { return this }
    }
    companion object { fun builder(): Builder<*> { return ValidationBuilder() } }

    abstract class Builder<B : Builder<B>>
    protected constructor() : ExceptionDetail.Builder<B>() {
        var errors: Map<String?, Set<String?>> = emptyMap()

        fun errors(errors: Map<String?, Set<String?>>): B { this.errors = errors; return self() }
        override fun build() = ValidationExceptionDetail(this)
    }
}