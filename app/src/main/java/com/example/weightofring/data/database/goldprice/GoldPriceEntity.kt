package com.example.weightofring.data.database.goldprice

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weightofring.data.network.GoldPriceApi
import com.example.weightofring.domain.model.GoldPriceForUi
import com.example.weightofring.utils.Constants.Companion.BASE_CURRENCY
import java.time.ZonedDateTime

@Entity
data class GoldPriceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val updateTime: Long,
    //metal
    val gold: Double,
    val silver: Double,
    val platinum: Double,
    val palladium: Double,
    //currency
    val russian_ruble: Double,
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
                base = goldPriceApi.base,
                updateTime = getLastUpdateTime(),
                gold = goldPriceApi.rates.XAU,
                silver = goldPriceApi.rates.XAG,
                platinum = goldPriceApi.rates.XPT,
                palladium = goldPriceApi.rates.XPD,
                russian_ruble = goldPriceApi.rates.RUB,
                canadian_dollar = goldPriceApi.rates.CAD,
                czech_koruna = goldPriceApi.rates.CZK,
                euro = goldPriceApi.rates.EUR,
                japanese_yen = goldPriceApi.rates.JPY,
                turkish_lira = goldPriceApi.rates.TRY,
                ukrainian_hryvnia = goldPriceApi.rates.UAH,
                united_arab_emirates_dirham = goldPriceApi.rates.AED
            )
        }

        fun mapToGoldPriceForUi(goldPriceEntity: GoldPriceEntity?): GoldPriceForUi? {
            if (goldPriceEntity == null) return null
            return GoldPriceForUi(
                timestamp = goldPriceEntity.timestamp,
                updateTime = goldPriceEntity.updateTime,
                metal = mutableListOf(
                    goldPriceEntity.gold,
                    goldPriceEntity.silver,
                    goldPriceEntity.platinum,
                    goldPriceEntity.palladium
                ),
                currency = mutableListOf(
                    BASE_CURRENCY.toDouble(),
                    goldPriceEntity.russian_ruble,
                    goldPriceEntity.canadian_dollar,
                    goldPriceEntity.czech_koruna,
                    goldPriceEntity.euro,
                    goldPriceEntity.japanese_yen,
                    goldPriceEntity.turkish_lira,
                    goldPriceEntity.ukrainian_hryvnia,
                    goldPriceEntity.united_arab_emirates_dirham
                )
            )
        }

        private fun getLastUpdateTime(): Long {
            return ZonedDateTime.now().toEpochSecond()
        }
    }
}
