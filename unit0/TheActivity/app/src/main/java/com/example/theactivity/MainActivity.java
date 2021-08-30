package com.example.theactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                Log.d("__OUTPUT", email + " " + password);
            }
        });

        Log.d("__LIFECYCLE", "onCreate CALLED");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("__LIFECYCLE", "onStart CALLED");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("__LIFECYCLE", "onResume CALLED");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("__LIFECYCLE", "onPause CALLED");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("__LIFECYCLE", "onStop CALLED");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("__LIFECYCLE", "onDestroy CALLED");
    }
}