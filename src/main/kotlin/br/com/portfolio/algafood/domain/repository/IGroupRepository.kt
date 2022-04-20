package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.Group
import org.springframework.stereotype.Repository

@Repository
interface IGroupRepository: ICustomJpaRepository<Group, Long> {

}