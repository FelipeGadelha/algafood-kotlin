package br.com.portfolio.algafood.api.handle

import br.com.portfolio.algafood.domain.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime

@ControllerAdvice
class ApiExceptionHandler(
    @Autowired private val messageSource: MessageSource
): ResponseEntityExceptionHandler() {

    companion object { const val DOCUMENTATION = ", Check the Documentation" }

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val exceptionDetail = ExceptionDetail.builder()
            .title(status.reasonPhrase)
            .status(status.value())
            .detail(ex.message)
            .developerMessage(ex::class.java.name)
            .timestamp(OffsetDateTime.now())
            .build()
        return ResponseEntity(exceptionDetail, HttpHeaders(), status)
    }
    override fun handleExceptionInternal(ex: Exception,
                                         body: Any?,
                                         headers: HttpHeaders,
                                         status: HttpStatus,
                                         request: WebRequest): ResponseEntity<Any> {

        val exceptionDetail = when (body) {
            is ExceptionStatus -> ExceptionDetail.builder()
                .status(status.value())
                .title(body.title)
                .type(body.uri)
                .detail(ex.message ?: "Erro Padrão")
                .developerMessage(ex::class.java.name)
                .timestamp(OffsetDateTime.now())
                .build()
            else -> ExceptionDetail.builder()
                .title(status.reasonPhrase + DOCUMENTATION)
                .status(status.value())
                .detail(ex.message)
                .developerMessage(ex::class.java.name)
                .timestamp(OffsetDateTime.now())
                .build()
        }
        return super.handleExceptionInternal(ex, exceptionDetail, headers, status, request)
    }

    override fun handleBindException(ex: BindException,
                                     headers: HttpHeaders,
                                     status: HttpStatus,
                                     request: WebRequest): ResponseEntity<Any> {
        return argumentNotValidException(ex, headers, status, request, ex.bindingResult)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        return argumentNotValidException(ex, headers, status, request, ex.bindingResult)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val exceptionDetail = ExceptionDetail.builder()
            .title(ExceptionStatus.RESOURCE_NOT_FOUND.title)
            .type(ExceptionStatus.RESOURCE_NOT_FOUND.uri)
            .status(status.value())
            .detail(ex.message ?: "Erro Padrão")
            .developerMessage(ex::class.java.name)
            .timestamp(OffsetDateTime.now())
            .build()
        return ResponseEntity(exceptionDetail, HttpHeaders(), status)
    }

    private fun argumentNotValidException(ex: Exception, headers: HttpHeaders, status: HttpStatus, request: WebRequest, bindingResult: BindingResult): ResponseEntity<Any> {
        val fieldErrors = bindingResult.fieldErrors
        val globalErrors = bindingResult.globalErrors

        var map: Map<String?, Set<String?>> = fieldErrors.groupBy{ field -> field.field }
            .mapValues{ it.value.map { field ->
                messageSource.getMessage(field, LocaleContextHolder.getLocale())
            }.toSet() }

        if (map.isEmpty()) {
            map = globalErrors.groupBy {global -> global.code }
                .mapValues{it.value.map { global -> global.defaultMessage }.toSet()}
        }
        val detail = ValidationExceptionDetail.builder()
            .title(ExceptionStatus.ARGUMENT_NOT_VALID.title + DOCUMENTATION)
            .status(status.value())
            .type(ExceptionStatus.ARGUMENT_NOT_VALID.uri)
            .detail("Check the error field(s)")
            .developerMessage(ex::class.java.name)
            .timestamp(OffsetDateTime.now())
            .errors(map)
            .build()
        return ResponseEntity(detail, headers,HttpStatus.BAD_REQUEST)
    }
}