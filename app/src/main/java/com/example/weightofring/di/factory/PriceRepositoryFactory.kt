package com.example.weightofring.di.factory

import android.app.Application
import com.example.weightofring.data.repositories.PriceRepositoryImpl
import com.example.weightofring.domain.reposytories.PriceRepository

object PriceRepositoryFactory {

    private var repository: PriceRepository? = null

    fun getPriceRepository(app: Application): PriceRepository {
        if (repository == null) {
            repository = PriceRepositoryImpl(app)

        }
        return repository!!
    }

}