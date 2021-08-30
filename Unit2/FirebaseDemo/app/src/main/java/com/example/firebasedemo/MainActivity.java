package com.example.firebasedemo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firebasedemo.models.Client;
import com.example.firebasedemo.viewmodels.ClientsViewModel;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientsViewModel viewModel = new ViewModelProvider(this).get(ClientsViewModel.class);
        viewModel.loadClients();

        EditText nameField = findViewById(R.id.name);
        EditText emailField = findViewById(R.id.email);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        findViewById(R.id.save).setOnClickListener((view) -> {
            viewModel.saveClient(nameField.getText().toString(), emailField.getText().toString());
        });

        viewModel.getClients().addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Client>>() {
            @Override
            public void onChanged(ObservableList<Client> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<Client> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList<Client> sender, int positionStart, int itemCount) {
                for (Client client : sender) {
                    // update the UI
                    Log.d("__FIREBASE", client.getName());
                }

            }

            @Override
            public void onItemRangeMoved(ObservableList<Client> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<Client> sender, int positionStart, int itemCount) {

            }
        });


    }
}