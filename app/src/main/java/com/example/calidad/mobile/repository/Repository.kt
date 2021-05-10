package com.example.calidad.mobile.repository

import androidx.lifecycle.MutableLiveData
import com.example.calidad.mobile.data.PollutionData

class Repository {

    companion object {
        val nearestCityPollution = MutableLiveData<PollutionData>()
    }
}