package com.cis436projects.weather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cis436projects.weather.R
import androidx.navigation.Navigation
import com.cis436projects.weather.databinding.MainFragmentBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class MainFragment : Fragment() {

    companion object;

    private lateinit var viewModel: MainViewModel
    // view binding
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get((MainViewModel::class.java))
        } ?: throw  Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.mainViewModel = viewModel
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&units=imperial&exclude=minutely,hourly,alerts,daily&appid=92ad99475c167a0a36786719e22fa78b"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    viewModel.setData(JSONObject(response.body()?.string().toString()))
                    binding.mainViewModel = viewModel
                } else {
                    Log.d("ResponseFail:", "Not successful")
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.sevenForcastButton.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_mainFragment_to_sevenForecastFragment
            )
        }
    }

}