package com.example.weightofring.domain

import androidx.annotation.DrawableRes
import com.example.weightofring.R
import com.example.weightofring.ui.fragments.gem_fragment.model.CutFormEnum
import com.example.weightofring.ui.fragments.gem_fragment.model.GemParametersEnum

object GemDrawablesStore {

    private val gemMap = hashMapOf<GemParametersEnum, Map<CutFormEnum, Int>>(
        GemParametersEnum.DIAMOND to hashMapOf(
            CutFormEnum.ROUND to R.drawable.rounddiamond,
            CutFormEnum.PRINCESS to R.drawable.princdiamond,
            CutFormEnum.OVAL to R.drawable.ovaldiamond,
            CutFormEnum.EMERALD to R.drawable.emerdiamond,
            CutFormEnum.BAGUETTE to R.drawable.bagdiamond,
            CutFormEnum.MARQUIS to R.drawable.marcdiamond
        ) ,
        GemParametersEnum.RUBIN to hashMapOf(
            CutFormEnum.ROUND to R.drawable.roundrubin,
            CutFormEnum.PRINCESS to R.drawable.princrub,
            CutFormEnum.OVAL to R.drawable.ovalrubi,
            CutFormEnum.EMERALD to R.drawable.emerrubi,
            CutFormEnum.BAGUETTE to R.drawable.bagrubi,
            CutFormEnum.MARQUIS to R.drawable.marcrubi
        ) ,
        GemParametersEnum.CITRINE to hashMapOf(
            CutFormEnum.ROUND to R.drawable.roundcitrin,
            CutFormEnum.PRINCESS to R.drawable.princcitrin,
            CutFormEnum.OVAL to R.drawable.ovalcitrin,
            CutFormEnum.EMERALD to R.drawable.emercitrin,
            CutFormEnum.BAGUETTE to R.drawable.bagcitrin,
            CutFormEnum.MARQUIS to R.drawable.marccitrin
        ) ,
        GemParametersEnum.EMERALD to hashMapOf(
            CutFormEnum.ROUND to R.drawable.roundemerald,
            CutFormEnum.PRINCESS to R.drawable.princemer,
            CutFormEnum.OVAL to R.drawable.ovalemer,
            CutFormEnum.EMERALD to R.drawable.emeremer,
            CutFormEnum.BAGUETTE to R.drawable.bagemer,
            CutFormEnum.MARQUIS to R.drawable.marcemer
        ) ,
        GemParametersEnum.AMETHYST to hashMapOf(
            CutFormEnum.ROUND to R.drawable.roundamethyst,
            CutFormEnum.PRINCESS to R.drawable.princamet,
            CutFormEnum.OVAL to R.drawable.ovalamet,
            CutFormEnum.EMERALD to R.drawable.emeramet,
            CutFormEnum.BAGUETTE to R.drawable.bagamet,
            CutFormEnum.MARQUIS to R.drawable.marcamet
         ) ,
        GemParametersEnum.AQUAMARINE to hashMapOf(
            CutFormEnum.ROUND to R.drawable.roundaqua,
            CutFormEnum.PRINCESS to R.drawable.princaqua,
            CutFormEnum.OVAL to R.drawable.ovalaqua,
            CutFormEnum.EMERALD to R.drawable.emeraqua,
            CutFormEnum.BAGUETTE to R.drawable.bagaqua,
            CutFormEnum.MARQUIS to R.drawable.marcaqua
        )
    )
    @DrawableRes
    fun getGemDrawable(gemType: GemParametersEnum, cutForm: CutFormEnum): Int? {
        val gemMapValue = gemMap[gemType] ?: return null
        return gemMapValue[cutForm]
    }
}