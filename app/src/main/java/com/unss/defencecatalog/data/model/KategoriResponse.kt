package com.unss.defencecatalog.data.model
class KatgoriResponse : ArrayList<KatgoriResponseItem>()

data class KatgoriResponseItem(
    val kategoriAdi: String,
    val kategoriResimUrl: String,
    val urunler: List<Urunler>
)

data class Urunler(
    val urunAdi: String,
    val urunDetay: String,
    val urunResim: String,
    val urunSirket: String
)