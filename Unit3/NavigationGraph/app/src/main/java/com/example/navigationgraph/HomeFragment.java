package com.example.navigationgraph;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationgraph.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        binding.toProfile.setOnClickListener(view -> {
            controller.navigate(R.id.action_homeFragment_to_profileFragment);
        });

        binding.toSettings.setOnClickListener(view -> {
            controller.navigate(R.id.action_homeFragment_to_settingsFragment);
        });

        return binding.getRoot();
    }
}