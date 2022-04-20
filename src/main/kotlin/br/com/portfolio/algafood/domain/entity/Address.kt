package br.com.portfolio.algafood.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Embeddable
data class Address(
    @Column(name = "address_cep") val cep: String = "",
    @Column(name = "address_place") val place: String = "",
    @Column(name = "address_number") val number: String = "",
    @Column(name = "address_complement") val complement: String? = null,
    @Column(name = "address_district") val district: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(*["hibernateLazyInitializer"]) //, "handler"})
    @JoinColumn(name = "address_city_id") val city: City = City()
)