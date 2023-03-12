package com.example.weightofring.domain.model

data class Lists(

    val listGemParameters: List<GemParameters> = listOf(
        GemParameters(
            nameGem = "Diamond",
            densityGem = "3.52",
            nameEnum = GemParameters.NameGemEnum.DIAMOND
        ),
        GemParameters(
            nameGem = "Rubin",
            densityGem = "3.99",
            nameEnum = GemParameters.NameGemEnum.RUBIN
        ),
        GemParameters(
            nameGem = "Emerald",
            densityGem = "2.71",
            nameEnum = GemParameters.NameGemEnum.EMERALD
        ),
        GemParameters(
            nameGem = "Сitrine",
            densityGem = "2.65",
            nameEnum = GemParameters.NameGemEnum.CITRINE
        ),
        GemParameters(
            nameGem = "Amethyst",
            densityGem = "2.65",
            nameEnum = GemParameters.NameGemEnum.AMETHYST
        ),
        GemParameters(
            nameGem = "Aquamarine",
            densityGem = "2.69",
            nameEnum = GemParameters.NameGemEnum.AQUAMARINE
        )
    ),

    val listCutParameters: List<CutType> = listOf(
        CutType(
            name = "Round",
            form = CutType.CutForm.ROUND,
            calculationCoefficient = "0.0018"
        ),
        CutType(
            name = "Princess",
            form = CutType.CutForm.PRINCESS,
            calculationCoefficient = "0.0023"
        ),
        CutType(
            name = "Oval",
            form = CutType.CutForm.OVAL,
            calculationCoefficient = "0.0018"
        ),
        CutType(
            name = "Emerald",
            form = CutType.CutForm.EMERALD,
            calculationCoefficient = "0.00245"
        ),
        CutType(
            name = "Baguette",
            form = CutType.CutForm.BAGUETTE,
            calculationCoefficient = "0.0029"
        ),
        CutType(
            name = "Marquis",
            form = CutType.CutForm.MARQUIS,
            calculationCoefficient = "0.0016"
        )
    )
)
