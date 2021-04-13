package com.cis436projects.weather.ui.main

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainViewModel : ViewModel() {
    private var temperature: Float? = null
    private var feelsLikeTemperature: Float? = null
    private var sunriseTime: String? = null
    private var sunsetTime: String? = null
    private var weatherConditions: String? = null
    private var precipitation: Float? = null
    private var windSpeed: Float? = null
    private var pressure: Float? = null
    private var humidity: Float? = null

    fun setData(weatherJSONObject: JSONObject?) {

        if (weatherJSONObject == null) {
            return
        }

        val currentJSONObject: JSONObject = weatherJSONObject["current"] as JSONObject
        val weatherDescJSONArray: JSONArray = currentJSONObject["weather"] as JSONArray
        val weatherDescJSONObject: JSONObject = weatherDescJSONArray[0] as JSONObject

        temperature = currentJSONObject["temp"].toString().toFloat()
        feelsLikeTemperature = currentJSONObject["feels_like"].toString().toFloat()
        sunriseTime = unixTimeToDate(currentJSONObject["sunrise"].toString().toLong())
        sunsetTime = unixTimeToDate(currentJSONObject["sunset"].toString().toLong())
        weatherConditions = weatherDescJSONObject["description"].toString()
        precipitation = currentJSONObject["dew_point"].toString().toFloat()
        windSpeed = currentJSONObject["wind_speed"].toString().toFloat()
        pressure = currentJSONObject["pressure"].toString().toFloat()
        humidity = currentJSONObject["humidity"].toString().toFloat()
    }

    fun getTemperature() : String {
        return "$temperature"
    }

    fun getFeelsLikeTemperature() : String {
        return "$feelsLikeTemperature"
    }

    fun getSunriseTime() : String {
        return "sunrise: $sunriseTime"
    }

    fun getSunsetTime() : String {
        return "sunset: $sunsetTime"
    }

    fun getWeatherConditions(): String {
        return "$weatherConditions"
    }

    fun getPrecipitation() : String {
        return "precipitation: $precipitation%"
    }

    fun getWindSpeed() : String {
        return "wind speed: $windSpeed mph"
    }

    fun getPressure() : String {
        return "Pressure: $pressure inHg"
    }

    fun getHumidity() : String {
        return "Humidity: $humidity%"
    }

    private fun unixTimeToDate(unixTimestamp: Long): String? {
        val sdf = SimpleDateFormat("h:ma", Locale.US)
        val date = Date(unixTimestamp * 1000)
        return sdf.format(date)
    }
}