package com.cis436projects.weather

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class WeatherData {
    private val url: String =
        "https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&units=imperial&exclude=minutely,hourly,alerts,daily&appid=92ad99475c167a0a36786719e22fa78b"
    var currentWeatherDataJSON: JSONObject = JSONObject()

    fun updateCurrentWeather() {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                currentWeatherDataJSON = JSONObject()
            }

            override fun onResponse(call: Call, response: Response) {
                currentWeatherDataJSON = if (response.isSuccessful) {
                    JSONObject(response.body()?.string().toString())
                } else {
                    Log.d("ResponseFail:", "Not successful")
                    JSONObject()
                }
            }
        })
    }
}