package com.cis436projects.weather

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class SevenForecastViewModel : ViewModel() {

    data class WeatherDay(val minTemp: Float, val maxTemp: Float, val precipitation: Float, val day: String) {
        fun getWeatherString() : String {
            return "$day - Min Temp: $minTemp - Max Temp: $maxTemp - Precipitation: $precipitation"
        }
    }

    private var weather: Array<WeatherDay> = Array(7) {
        WeatherDay(
            0f,
            0f,
            0f,
            "Sunday"
        )
    }

    fun setData(weatherJSONObject: JSONObject?) {
        if (weatherJSONObject == null) {
            return
        }

        val dailyJSONArray: JSONArray = weatherJSONObject["daily"] as JSONArray

        for (index in 0..6) {
            val currentJSONObject: JSONObject = dailyJSONArray[index] as JSONObject
            val tempJSONObject: JSONObject = currentJSONObject["temp"] as JSONObject

            val minTemp = tempJSONObject["min"].toString().toFloat()
            val maxTemp = tempJSONObject["max"].toString().toFloat()
            val precipitation = currentJSONObject["dew_point"].toString().toFloat()
            val day = unixTimeToDate(currentJSONObject["sunrise"].toString().toLong())

            weather[index] = WeatherDay(minTemp, maxTemp, precipitation, day)
        }

    }

    fun getWeatherString(index: Int) : String {
        return weather[index].getWeatherString()
    }

    private fun unixTimeToDate(unixTimestamp: Long): String {
        val sdf = SimpleDateFormat("E", Locale.US)
        val date = Date(unixTimestamp * 1000)
        return sdf.format(date)
    }
}