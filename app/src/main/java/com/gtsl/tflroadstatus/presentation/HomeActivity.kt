package com.gtsl.tflroadstatus.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gtsl.tflroadstatus.R
import com.gtsl.tflroadstatus.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityHomeBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(toolbar)
            NavigationUI.setupWithNavController(toolbar, findNavController(R.id.fragment_placeholder))
        }
    }
}