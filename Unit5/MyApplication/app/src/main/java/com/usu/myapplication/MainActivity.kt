package com.usu.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.usu.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var count = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.increment.setOnClickListener {
            count += 1
            binding.count.text = "" + count
        }

        binding.decrement.setOnClickListener {
            count -= 1;
            binding.count.text = "" + count
        }
    }
}