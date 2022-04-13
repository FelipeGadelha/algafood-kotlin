package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.State
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StateRepository: JpaRepository<State, Long> {

}