package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.fragments.ui.HomeFragment;
import com.example.fragments.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    String currentFragment = "home";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragmentContainerView, HomeFragment.class, null)
                .setReorderingAllowed(true)
                .commit();

        findViewById(R.id.homeButton).setOnClickListener((view) -> {
            if (!currentFragment.equals("home")) {
                currentFragment = "home";
                manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        findViewById(R.id.profileButton).setOnClickListener((view) -> {
            if (!currentFragment.equals("profile")) {
                currentFragment = "profile";
                manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });
    }
}