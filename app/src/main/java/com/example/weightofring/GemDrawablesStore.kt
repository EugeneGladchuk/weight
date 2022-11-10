package com.example.weightofring

import com.example.weightofring.CalculateGemFragment.CutType.CutForm
import com.example.weightofring.CalculateGemFragment.GemParameters.NameGemEnum

object GemDrawablesStore {

    private val gemMap = hashMapOf<NameGemEnum, Map<CutForm, Int>>(
        NameGemEnum.DIAMOND to hashMapOf(
            CutForm.ROUND to R.drawable.rounddiamond,
            CutForm.PRINCESS to R.drawable.princdiamond,
            CutForm.OVAL to R.drawable.ovaldiamond,
            CutForm.EMERALD to R.drawable.emerdiamond,
            CutForm.BAGUETTE to R.drawable.bagdiamond,
            CutForm.MARQUIS to R.drawable.marcdiamond
        ) ,
        NameGemEnum.RUBIN to hashMapOf(
            CutForm.ROUND to R.drawable.roundrubin,
            CutForm.PRINCESS to R.drawable.princrub,
            CutForm.OVAL to R.drawable.ovalrubi,
            CutForm.EMERALD to R.drawable.emerrubi,
            CutForm.BAGUETTE to R.drawable.bagrubi,
            CutForm.MARQUIS to R.drawable.marcrubi
        ) ,
        NameGemEnum.CITRINE to hashMapOf(
            CutForm.ROUND to R.drawable.roundcitrin,
            CutForm.PRINCESS to R.drawable.princcitrin,
            CutForm.OVAL to R.drawable.ovalcitrin,
            CutForm.EMERALD to R.drawable.emercitrin,
            CutForm.BAGUETTE to R.drawable.bagcitrin,
            CutForm.MARQUIS to R.drawable.marccitrin
        ) ,
        NameGemEnum.EMERALD to hashMapOf(
            CutForm.ROUND to R.drawable.roundemerald,
            CutForm.PRINCESS to R.drawable.princemer,
            CutForm.OVAL to R.drawable.ovalemer,
            CutForm.EMERALD to R.drawable.emeremer,
            CutForm.BAGUETTE to R.drawable.bagemer,
            CutForm.MARQUIS to R.drawable.marcemer
        ) ,
        NameGemEnum.AMETHYST to hashMapOf(
            CutForm.ROUND to R.drawable.roundamethyst,
            CutForm.PRINCESS to R.drawable.princamet,
            CutForm.OVAL to R.drawable.ovalamet,
            CutForm.EMERALD to R.drawable.emeramet,
            CutForm.BAGUETTE to R.drawable.bagamet,
            CutForm.MARQUIS to R.drawable.marcamet
         ) ,
        NameGemEnum.AQUAMARINE to hashMapOf(
            CutForm.ROUND to R.drawable.roundaqua,
            CutForm.PRINCESS to R.drawable.princaqua,
            CutForm.OVAL to R.drawable.ovalaqua,
            CutForm.EMERALD to R.drawable.emeraqua,
            CutForm.BAGUETTE to R.drawable.bagaqua,
            CutForm.MARQUIS to R.drawable.marcaqua
        )
    )

    fun getGemDrawable(cutForm: CutForm, gemType: NameGemEnum): Int? {
        val gemMapValue = gemMap[gemType] ?: return null
        return gemMapValue[cutForm]
    }

}