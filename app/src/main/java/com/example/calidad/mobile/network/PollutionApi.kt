package com.example.calidad.mobile.network

import com.example.calidad.mobile.data.PollutionData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PollutionApi {

    @GET("/v2/nearest_city")
    fun getPollutionNearestCityAsync(@Query("lat") lat:Double, @Query("lon") lon:Double): Deferred<Response<PollutionData>>
}