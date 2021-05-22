package com.example.calidad.mobile.ui.search

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.calidad.databinding.SearchFragmentBinding
import com.example.calidad.mobile.repository.Repository
import com.google.android.material.snackbar.Snackbar

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var geocoder:Geocoder

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        geocoder = Geocoder(requireContext())

        Repository.nearestCityPollution.observe(viewLifecycleOwner, { setProgress(false) })

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

        binding.topNameTextView.text = Repository.cityName

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWeather(place: String) {
        try {
            setProgress(true)
            viewModel.setupWeather(geocoder,place)
            binding.topNameTextView.text = Repository.cityName
        } catch (e: Exception) {
            Snackbar.make(binding.searchLayout, "Unfortunately we couldn't trace the place", Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun setProgress(status: Boolean) {
        binding.progressBar.isVisible = status

    }
}
