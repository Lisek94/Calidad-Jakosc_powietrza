package com.example.calidad.mobile.ui.items

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.calidad.databinding.ItemsFragmentBinding
import com.example.calidad.mobile.data.PollutionData
import com.example.calidad.mobile.repository.Repository

class ItemsFragment : Fragment() {

    private var _binding: ItemsFragmentBinding? = null
    private val viewModel by viewModels<ItemsViewModel>()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ItemsFragmentBinding.inflate(inflater, container, false)

        Repository.nearestCityPollution.observe(viewLifecycleOwner, { updateUI(it) })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(data: PollutionData) {
        val temp = "${data.data.current.weather.tp}°C"
        binding.numberTemperatureTextView.text = temp

        val humidity = "${data.data.current.weather.hu}%"
        binding.numberHumidityTextView.text = humidity

        val quality = "${data.data.current.pollution.aqius} AQI"
        binding.qualityTextView.text = quality
        binding.qualityTextView.setTextColor(viewModel.setupColor(data.data.current.pollution.aqius))

        val pressure = "${data.data.current.weather.pr} hPa"
        binding.numberPressureTextView.text = pressure
    }
}