package com.example.calidad.mobile.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calidad.mobile.data.PollutionData
import com.example.calidad.mobile.network.RetrofitClient
import com.example.calidad.mobile.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel : ViewModel() {

    fun fetchPollution(lat: Double, lon: Double) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            val pollutionData = getNearestCity(lat,lon)?.body()
            if (pollutionData != null){
                Repository.nearestCityPollution.value = pollutionData
            }
        }
    }

    private suspend fun getNearestCity(lat: Double, lon: Double): Response<PollutionData>? {
        val pollutionApi = RetrofitClient.instance
            return try {
                pollutionApi.getPollutionNearestCityAsync(lat, lon).await()
            } catch (t:Throwable) {
                Log.d("LOG", t.message.toString())
                null
            }
    }
}