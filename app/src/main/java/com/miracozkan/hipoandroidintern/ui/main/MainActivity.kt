package com.miracozkan.hipoandroidintern.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miracozkan.hipoandroidintern.R
import com.miracozkan.hipoandroidintern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.title = getString(R.string.members)
        setContentView(binding.root)
    }
}
