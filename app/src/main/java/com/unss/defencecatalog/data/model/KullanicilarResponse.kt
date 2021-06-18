package com.unss.defencecatalog.data.model

data class KullanicilarResponse(
    val Kullanicilar: List<Kullanicilar>
)

data class Kullanicilar(
    val AdiSoyadi: String,
    val Email: String,
    val Sifre: String
)