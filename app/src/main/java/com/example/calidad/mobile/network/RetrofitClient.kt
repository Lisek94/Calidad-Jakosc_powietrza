package com.example.calidad.mobile.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val base_url = "https://api.airvisual.com"
    private const val api_key = "c14761d8-6d27-40c4-a6e8-84fe1b3be0d5"


    private val client = OkHttpClient.Builder()
            .addInterceptor{
                var request = it.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("key", api_key)
                    .build()
                request = request.newBuilder().url(url).build()
                it.proceed(request)
            }.build()


    val instance: PollutionApi by lazy {
       val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        retrofit.create(PollutionApi::class.java)
    }
}