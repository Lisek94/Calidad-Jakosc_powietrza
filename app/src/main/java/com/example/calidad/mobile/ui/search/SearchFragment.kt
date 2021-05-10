package com.example.calidad.mobile.ui.search

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.calidad.databinding.SearchFragmentBinding
import com.google.android.material.snackbar.Snackbar

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var geocoder:Geocoder


    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        geocoder = Geocoder(requireContext())

        binding.searchCityEditText.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchCityEditText.clearFocus()
                binding.searchCityEditText.setQuery("", false)
                if (query != null) {
                    getWeather(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateCity(lat: Double, lon: Double) {
        val cityName = geocoder.getFromLocation(lat,lon,1)[0].locality
        binding.topNameTextView.text = cityName
    }

    private fun getWeather(place: String) {
        try {
            val list = geocoder.getFromLocationName(place, 1)
            val lat = list[0].latitude
            val lon = list[0].longitude
            viewModel.fetchPollution(lat,lon)
            updateCity(lat, lon)
        } catch (e: Exception) {
            Snackbar.make(binding.searchLayout, "Unfortunately we couldn't trace the place", Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
