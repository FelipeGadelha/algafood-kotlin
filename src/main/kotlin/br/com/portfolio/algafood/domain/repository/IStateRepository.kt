package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.State
import org.springframework.stereotype.Repository

@Repository
interface IStateRepository: ICustomJpaRepository<State, Long> {

}