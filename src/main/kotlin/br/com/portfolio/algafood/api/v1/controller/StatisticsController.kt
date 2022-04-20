package br.com.portfolio.algafood.api.v1.controller

import br.com.portfolio.algafood.api.validator.annotation.Offset
import br.com.portfolio.algafood.domain.entity.DailySale
import br.com.portfolio.algafood.domain.filter.DailySaleFilter
import br.com.portfolio.algafood.domain.search.SalesSearch
import br.com.portfolio.algafood.domain.service.SalesReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/statistics")
@Validated
class StatisticsController @Autowired constructor(
    private val salesSearch: SalesSearch,
    private val salesReportService: SalesReportService
) {
    @GetMapping("/daily-sale")
    fun findDailySale(
        filter: DailySaleFilter,
        @RequestParam(required = false, defaultValue = "+00:00") @Offset offset: String
    ): List<DailySale> = salesSearch.findDailySale(filter, offset)


    @GetMapping(path = ["/daily-sale"], produces = [MediaType.APPLICATION_PDF_VALUE])
    fun findDailySalePdf(filter: DailySaleFilter,
        @RequestParam(required = false, defaultValue = "+00:00") @Offset offset: String
    ): ResponseEntity<ByteArray> {
        val bytesPdf = salesReportService.emitDailySales(filter, offset)
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sale.pdf")
        headers.add(HttpHeaders.CONTENT_LENGTH, bytesPdf.size.toString())
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .headers(headers)
            .body(bytesPdf)
    }
}