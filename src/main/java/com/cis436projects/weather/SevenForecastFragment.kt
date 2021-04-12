package com.cis436projects.weather

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cis436projects.weather.databinding.SevenForecastFragmentBinding

class SevenForecastFragment : Fragment() {

    companion object {
        fun newInstance() = SevenForecastFragment()
    }

    private lateinit var viewModel: SevenForecastViewModel
    // view binding
    private var _binding: SevenForecastFragmentBinding? = null
    private val binding get() = _binding!!

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SevenForecastFragmentBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SevenForecastViewModel::class.java)

        binding.currentDayButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

}