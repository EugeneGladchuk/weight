package com.example.weightofring.di.factory

import android.app.Application
import com.example.weightofring.data.repositories.GemRepositoryImpl
import com.example.weightofring.domain.reposytories.GemRepository

object GemRepositoryFactory {

    private var repository: GemRepository? = null

    fun getGemRepository(app: Application): GemRepository {
        if (repository == null) {
            repository = GemRepositoryImpl(app)
        }
        return repository!!
    }

}