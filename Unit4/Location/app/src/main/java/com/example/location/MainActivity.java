package com.example.location;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResultLauncher<String> launcher =
                registerForActivityResult(
                        new ActivityResultContracts.RequestPermission(),
                        isGranted -> {
                            if (isGranted) {
                                System.out.println("User granted permission");
                                getAndDisplayLocation();
                            } else {
                                System.out.println("User denied permission");
                            }
                        }
                );


        findViewById(R.id.button).setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                System.out.println("User alread has given us permission, setup location services");
                getAndDisplayLocation();
            } else {
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        });
    }

    private void getAndDisplayLocation() {
        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(location.toString());
                    }
                }
            });

            LocationRequest request = LocationRequest.create()
//                    .setInterval(1000)
                    .setInterval(1)
//                    .setSmallestDisplacement(.0001f)
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            locationClient.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    System.out.println("updated");
                    Location location = locationResult.getLastLocation();
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(location.getLatitude() + " " + location.getLongitude());
                }
            }, Looper.getMainLooper());
        }

    }
}