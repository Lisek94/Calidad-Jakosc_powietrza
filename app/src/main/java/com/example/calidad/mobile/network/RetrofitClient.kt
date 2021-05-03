package com.example.calidad.mobile.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val base_url = "http://api.airvisual.com/"
    private const val api_key = "c14761d8-6d27-40c4-a6e8-84fe1b3be0d5"


    private val client = OkHttpClient.Builder()
            .addInterceptor{chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                        .addQueryParameter("key", api_key)
                        .build()
                request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()


    val instance: PollutionApi by lazy {
        Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(PollutionApi::class.java)
    }
}