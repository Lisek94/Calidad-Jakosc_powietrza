package com.example.calidad.mobile.ui.search

import android.location.Geocoder
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

    fun setupWeather(geocoder: Geocoder, place:String){
        val list = geocoder.getFromLocationName(place, 1)
        val lat = list[0].latitude
        val lon = list[0].longitude
        fetchPollution(lat,lon)
        updateCity(geocoder,lat, lon)
    }

    private fun updateCity(geocoder:Geocoder, lat: Double, lon: Double) {
        val cityName = geocoder.getFromLocation(lat,lon,1)[0].locality
        Repository.cityName = cityName
    }

    private fun fetchPollution(lat: Double, lon: Double) {
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