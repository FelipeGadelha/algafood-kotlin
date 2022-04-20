package br.com.portfolio.algafood

import br.com.portfolio.algafood.infra.repository.CustomJpaRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepository::class)
class AlgafoodApplication

fun main(args: Array<String>) {
	runApplication<AlgafoodApplication>(*args)
}
