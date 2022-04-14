package br.com.portfolio.algafood.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun openApi() = OpenAPI()
        .info(
            Info()
                .title("Algafood API")
                .description("Spring Algafood API sample application")
                .version("v1.0")
                .contact(
                    Contact()
                        .name("Felipe Gadelha Diniz da Silva")
                        .url("https://www.linkedin.com/in/felipe-gadelha-diniz-da-silva-aaaa4a158/")
                        .email("felipegadelha90@gmail.com")
                ).license(
                    License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")
                )
        ).externalDocs(
            ExternalDocumentation()
                .description("Spring Algafood Github Documentation")
                .url("https://github.com/FelipeGadelha/algafood-kotlin")
        )
}