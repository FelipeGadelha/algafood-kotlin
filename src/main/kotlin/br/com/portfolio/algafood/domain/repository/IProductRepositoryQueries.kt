package br.com.portfolio.algafood.domain.repository

import br.com.portfolio.algafood.domain.entity.ProductImage

interface IProductRepositoryQueries {
    fun save(photo: ProductImage): ProductImage
    fun remove(photo: ProductImage)
}
