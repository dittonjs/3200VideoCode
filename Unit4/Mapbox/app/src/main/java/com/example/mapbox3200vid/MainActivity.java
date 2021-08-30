package com.example.mapbox3200vid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.os.PersistableBundle;
import android.widget.TextView;

import com.example.mapbox3200vid.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.OnLocationCameraTransitionListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.BackgroundLayer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mapboxMap.onCreate(savedInstanceState);
        mapView = binding.mapboxMap;

        ActivityResultLauncher<String> launcher =
                registerForActivityResult(
                        new ActivityResultContracts.RequestPermission(),
                        isGranted -> {
                                if (isGranted) {
                                    setupMap();
                                }
                        }
                );

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            setupMap();
        } else {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    public void setupMap() {
        mapView.getMapAsync(map -> {
            map.setStyle(Style.MAPBOX_STREETS, style -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(this);

                    LocationRequest request = LocationRequest.create()
                            .setInterval(1)
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    ArrayList<Point> points = new ArrayList<>();
                    GeoJsonSource source = new GeoJsonSource("location-history-source", Feature.fromGeometry(
                            LineString.fromLngLats(points)
                    ));
                    style.addSource(source);
                    LineLayer layer = new LineLayer("history", "location-history-source")
                            .withProperties(
                                    PropertyFactory.lineDasharray(new Float[]{.01f, 2f}),
                                    PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                                    PropertyFactory.lineWidth(5f),
                                    PropertyFactory.lineColor(Color.RED)
                            );
                    style.addLayer(layer);
                    locationClient.requestLocationUpdates(request, new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            // our code here
                            points.add(
                                    Point.fromLngLat(
                                            locationResult.getLastLocation().getLongitude(),
                                            locationResult.getLastLocation().getLatitude()
                                    )
                            );
                            source.setGeoJson(Feature.fromGeometry(
                                    LineString.fromLngLats(points)
                            ));

                        }
                    }, Looper.getMainLooper());

                    LocationComponent component = map.getLocationComponent();
                    component.activateLocationComponent(LocationComponentActivationOptions.builder(this, style).build());
                    component.setLocationComponentEnabled(true);
                    component.setRenderMode(RenderMode.NORMAL);
                    component.setCameraMode(CameraMode.TRACKING, 750, 16.0, 0.0, 0.0, new OnLocationCameraTransitionListener() {
                        @Override
                        public void onLocationCameraTransitionFinished(int cameraMode) {
                            component.zoomWhileTracking(16);
                        }

                        @Override
                        public void onLocationCameraTransitionCanceled(int cameraMode) {

                        }
                    });
                }
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}