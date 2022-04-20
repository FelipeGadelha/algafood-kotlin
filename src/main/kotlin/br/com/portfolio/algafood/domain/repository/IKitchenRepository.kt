package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.Kitchen
import org.springframework.stereotype.Repository

@Repository
interface IKitchenRepository: ICustomJpaRepository<Kitchen, Long> {

}