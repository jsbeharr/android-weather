package com.cis436projects.weather

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.cis436projects.weather.databinding.SevenForecastFragmentBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class SevenForecastFragment : Fragment() {

    companion object;

    private lateinit var viewModel: SevenForecastViewModel
    // view binding
    private var _binding: SevenForecastFragmentBinding? = null
    private val binding get() = _binding!!

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get((SevenForecastViewModel::class.java))
        } ?: throw  Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.seven_forecast_fragment, container, false)
        binding.sevenForecastViewModel = viewModel
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&units=imperial&exclude=current,minutely,hourly,alerts&appid=92ad99475c167a0a36786719e22fa78b"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    viewModel.setData(JSONObject(response.body()?.string().toString()))
                    binding.sevenForecastViewModel = viewModel
                } else {
                    Log.d("ResponseFail:", "Not successful")
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.currentDayButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

}