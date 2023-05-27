package com.example.weightofring.di.factory

import android.app.Application
import com.example.weightofring.data.repositories.RingRepositoryImpl
import com.example.weightofring.domain.reposytories.RingRepository

object RingRepositoryFactory {

    private var repository: RingRepository? = null

    fun getRingRepository(app: Application): RingRepository {
        if (repository == null) {
            repository = RingRepositoryImpl(app)
        }
        return repository!!
    }
}