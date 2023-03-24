package com.example.weightofring.domain.model

import com.example.weightofring.ui.fragments.gem_fragment.model.GemParametersEnum

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
        CutFormEnum.MARQUIS,
    )
}


