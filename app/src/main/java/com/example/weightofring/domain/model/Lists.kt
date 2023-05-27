package com.example.weightofring.domain.model

import com.example.weightofring.ui.fragments.gem_fragment.model.CutFormEnum
import com.example.weightofring.ui.fragments.gem_fragment.model.GemParametersEnum
import com.example.weightofring.ui.fragments.price_fragment.model.*

object Lists {

    val listGemParameters: List<GemParametersEnum> = listOf(
        GemParametersEnum.DIAMOND,
        GemParametersEnum.RUBIN,
        GemParametersEnum.EMERALD,
        GemParametersEnum.CITRINE,
        GemParametersEnum.AMETHYST,
        GemParametersEnum.AQUAMARINE
    )

    val listCutParameters: List<CutFormEnum> = listOf(
        CutFormEnum.ROUND,
        CutFormEnum.PRINCESS,
        CutFormEnum.OVAL,
        CutFormEnum.EMERALD,
        CutFormEnum.BAGUETTE,
        CutFormEnum.MARQUIS
    )

    val listForUnitSpinner: List<UnitEnum> = listOf(
        UnitEnum.OUNCE_TROY,
        UnitEnum.GRAM
    )

    val listCurrency: MutableList<CurrencyForSpinner> = mutableListOf(
        CurrencyForSpinner(currencyName = "US Dollar", currencyNameEnum = CurrencyEnum.US_DOLLAR),
        CurrencyForSpinner(currencyName = "Russian Ruble", currencyNameEnum = CurrencyEnum.RUSSIAN_RUBLE),
        CurrencyForSpinner(currencyName = "Canadian Dollar", currencyNameEnum = CurrencyEnum.CANADIAN_DOLLAR),
        CurrencyForSpinner(currencyName = "Czech Koruna", currencyNameEnum = CurrencyEnum.CZECH_KORUNA),
        CurrencyForSpinner(currencyName = "Euro", currencyNameEnum = CurrencyEnum.EURO),
        CurrencyForSpinner(currencyName = "Japanese Yen", currencyNameEnum = CurrencyEnum.JAPANESE_YEN),
        CurrencyForSpinner(currencyName = "Turkish Lira", currencyNameEnum = CurrencyEnum.TURKISH_LIRA),
        CurrencyForSpinner(currencyName = "Ukrainian Hryvnia", currencyNameEnum = CurrencyEnum.UKRAINIAN_HRYVNIA),
        CurrencyForSpinner(currencyName = "UAE Dirham", currencyNameEnum = CurrencyEnum.UAE_DIRHAM)
    )

    val listMetal: MutableList<MetalForSpinner> = mutableListOf(
        MetalForSpinner(metalName = "Gold", metalNameEnum = MetalEnum.GOLD),
        MetalForSpinner(metalName = "Silver", metalNameEnum = MetalEnum.SILVER),
        MetalForSpinner(metalName = "Platinum", metalNameEnum = MetalEnum.PLATINUM),
        MetalForSpinner(metalName = "Palladium", metalNameEnum = MetalEnum.PALLADIUM)
    )

}


