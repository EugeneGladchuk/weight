package com.example.weightofring.data.database.goldprice

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weightofring.data.network.GoldPriceApi
import com.example.weightofring.domain.model.Currency
import com.example.weightofring.domain.model.GoldPriceForUi
import com.example.weightofring.domain.model.Metal

@Entity
data class GoldPriceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val success: Boolean,
    val timestamp: Long,
    val date: String,
    val base: String,
    //metal
    val gold: Double,
    val silver: Double,
    val platinum: Double,
    val bronze: Double,
    val titanium: Double,
    val rhodium: Double,
    val ruthenium: Double,
    //currency
    val russian_ruble: Double,
    val united_states_dollar: Double,
    val canadian_dollar: Double,
    val czech_koruna: Double,
    val euro: Double,
    val japanese_yen: Double,
    val turkish_lira: Double,
    val ukrainian_hryvnia: Double,
    val united_arab_emirates_dirham: Double
) {
    companion object {
        fun mapFromGoldPrice(goldPriceApi: GoldPriceApi): GoldPriceEntity{
            return GoldPriceEntity(
                success = goldPriceApi.success,
                timestamp = goldPriceApi.timestamp,
                date = goldPriceApi.date,
                base = goldPriceApi.base,
                gold = goldPriceApi.rates.XAU,
                silver = goldPriceApi.rates.XAG,
                platinum = goldPriceApi.rates.XPT,
                bronze = goldPriceApi.rates.BRONZE,
                titanium = goldPriceApi.rates.TITANIUM,
                rhodium = goldPriceApi.rates.XRH,
                ruthenium = goldPriceApi.rates.RUTH,
                russian_ruble = goldPriceApi.rates.RUB,
                united_states_dollar = goldPriceApi.rates.USD,
                canadian_dollar = goldPriceApi.rates.CAD,
                czech_koruna = goldPriceApi.rates.CZK,
                euro = goldPriceApi.rates.EUR,
                japanese_yen = goldPriceApi.rates.JPY,
                turkish_lira = goldPriceApi.rates.TRY,
                ukrainian_hryvnia = goldPriceApi.rates.UAH,
                united_arab_emirates_dirham = goldPriceApi.rates.AED
            )
        }

        fun mapToGoldPriceForUi(goldPriceEntity: GoldPriceEntity): GoldPriceForUi {
            return GoldPriceForUi(
                timestamp = goldPriceEntity.timestamp,
                date = goldPriceEntity.date,
                metal = Metal(
                    gold = goldPriceEntity.gold,
                    silver = goldPriceEntity.silver,
                    platinum = goldPriceEntity.platinum,
                    bronze = goldPriceEntity.bronze,
                    titanium = goldPriceEntity.titanium,
                    rhodium = goldPriceEntity.rhodium,
                    ruthenium = goldPriceEntity.ruthenium
                ),
                currency = Currency(
                    russianRuble = goldPriceEntity.russian_ruble,
                    unitedStatesDollar = goldPriceEntity.united_states_dollar,
                    canadianDollar = goldPriceEntity.canadian_dollar,
                    czechKoruna = goldPriceEntity.czech_koruna,
                    euro = goldPriceEntity.euro,
                    japaneseYen = goldPriceEntity.japanese_yen,
                    turkishLira = goldPriceEntity.turkish_lira,
                    ukrainianHryvnia = goldPriceEntity.ukrainian_hryvnia,
                    unitedArabEmiratesDirham = goldPriceEntity.united_arab_emirates_dirham
                )
            )
        }
    }
}
