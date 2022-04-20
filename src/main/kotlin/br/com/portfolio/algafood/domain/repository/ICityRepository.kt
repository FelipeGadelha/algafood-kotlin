package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.City
import org.springframework.stereotype.Repository

@Repository
interface ICityRepository: ICustomJpaRepository<City, Long> {

}