package com.example.weightofring.data.network

import com.example.weightofring.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface GoldPriceService {
    @GET("latest")
    suspend fun getLatestPrice(
        @Query("access_key") access_key: String
    ): GoldPrice
}

object PriceApi {
    val retrofitService : GoldPriceService by lazy {
        retrofit.create(GoldPriceService::class.java) }
}
