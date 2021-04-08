package com.cis436projects.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cis436projects.weather.ui.main.MainFragment
import android.net.Uri

class MainActivity : AppCompatActivity(), SevenForecastFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

}