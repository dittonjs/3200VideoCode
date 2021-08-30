package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.databinding.TestLayoutBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        CountViewModel viewmodel = new ViewModelProvider(this).get(CountViewModel.class);

        binding.setViewmodel(viewmodel);
        setContentView(binding.getRoot());
    }
}