package com.cis436projects.weather

import androidx.lifecycle.ViewModel

class SevenForecastViewModel : ViewModel() {
    private var temperature: Float? = null
    private var feelsLikeTemperature: Float? = null
    private var minTemp: Float? = null
    private var maxTemp: Float? = null
    private var weatherConditions: String? = null
    private var precipitation: Float? = null
    private var windSpeed: Float? = null
    private var pressure: Float? = null
    private var humidity: Float? = null
}