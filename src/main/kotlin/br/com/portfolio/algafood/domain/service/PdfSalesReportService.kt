package br.com.portfolio.algafood.domain.service

import br.com.portfolio.algafood.domain.filter.DailySaleFilter
import br.com.portfolio.algafood.domain.search.SalesSearch
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.design.JasperDesign
import net.sf.jasperreports.engine.xml.JRXmlLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PdfSalesReportService @Autowired constructor(
    private val salesSearch: SalesSearch
) : SalesReportService {
    override fun emitDailySales(filter: DailySaleFilter, offset: String): ByteArray {
        return try {
            val inputStream = this.javaClass
                .getResourceAsStream("/reports/daily-sales.jasper")
            val params = HashMap<String, Any>()
            params["REPORT_LOCALE"] = Locale("pt", "BR")
            val dailySales = salesSearch.findDailySale(filter, offset)
            val dataSource = JRBeanCollectionDataSource(dailySales, false)
            val jasperDesign: JasperDesign = JRXmlLoader.load(inputStream)
            val jasperReport: JasperReport = JasperCompileManager.compileReport(jasperDesign)
            //            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
            //            JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/thermochart.pdf");
            val jasperPrint: JasperPrint? = JasperFillManager.fillReport(jasperReport, params, dataSource)
            JasperExportManager.exportReportToPdf(jasperPrint)
        } catch (e: Exception) {
            throw RuntimeException("Teste: " + e.message)
        }
    }
}