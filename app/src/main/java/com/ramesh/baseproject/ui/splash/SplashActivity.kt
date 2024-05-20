package com.ramesh.baseproject.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View

import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ramesh.baseproject.databinding.ActivitySplashBinding
import com.ramesh.baseproject.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val displayLength: Long = 3000
    private lateinit var binding: ActivitySplashBinding
    val viewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater);
        setContentView(binding.root);

        Handler(mainLooper).postDelayed(Runnable { /* Create an Intent that will start the Next - Activity. */
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, displayLength)
    }


}