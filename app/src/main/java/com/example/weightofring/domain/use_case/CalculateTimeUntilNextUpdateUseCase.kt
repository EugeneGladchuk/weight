package com.example.weightofring.domain.use_case

import java.time.ZonedDateTime

class CalculateTimeUntilNextUpdateUseCase {
    fun calculateTime(updateTime: Long): Boolean {

        val timeOfNextUpdate = updateTime + 86400
        val currentTime = ZonedDateTime.now().toEpochSecond()

        return currentTime > timeOfNextUpdate
    }

    fun calculateAvailableDate(updateTime: Long): Long {
        return updateTime + 86400
    }
}