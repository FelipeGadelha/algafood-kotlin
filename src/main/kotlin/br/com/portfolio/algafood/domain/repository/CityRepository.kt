package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.City
import br.com.portfolio.algafood.domain.entity.Kitchen
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository: JpaRepository<City, Long> {

}