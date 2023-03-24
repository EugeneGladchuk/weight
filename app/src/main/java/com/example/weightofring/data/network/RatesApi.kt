package com.example.weightofring.data.network

import com.squareup.moshi.Json

data class RatesApi(
    //metal
    @Json(name = "gold") val XAU: Double,
    @Json(name = "silver") val XAG: Double,
    @Json(name = "platinum") val XPT: Double,
    @Json(name = "bronze") val BRONZE: Double,
    @Json(name = "titanium") val TITANIUM: Double,
    @Json(name = "rhodium") val XRH: Double,
    @Json(name = "ruthenium") val RUTH: Double,
    //currency
    @Json(name = "russian_ruble") val RUB: Double,
    @Json(name = "united_states_dollar") val USD: Double,
    @Json(name = "canadian_dollar") val CAD: Double,
    @Json(name = "czech_koruna") val CZK: Double,
    @Json(name = "euro") val EUR: Double,
    @Json(name = "japanese_yen") val JPY: Double,
    @Json(name = "turkish_lira") val TRY: Double,
    @Json(name = "ukrainian_hryvnia") val UAH: Double,
    @Json(name = "united_arab_emirates_dirham") val AED: Double
)
