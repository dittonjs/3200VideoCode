package com.example.firebasedemo.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.firebasedemo.models.Client;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClientsViewModel extends ViewModel {
    ObservableArrayList<Client> clients = new ObservableArrayList<>();
    FirebaseFirestore db;

    public ClientsViewModel() {
        db = FirebaseFirestore.getInstance();
    }

    public ObservableArrayList<Client> getClients() {
        return clients;
    }

    public void saveClient(String name, String email) {
        Client newClient = new Client(
                name,
                email
        );
        db.collection("clients").add(newClient).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                clients.add(newClient);
            } else {
                // use databinding to display an error
            }
        });

    }

    public void loadClients() {
        db.collection("clients")
                .get()
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot collection = task.getResult();
                        clients.addAll(collection.toObjects(Client.class));
                    } else {
                        // display error
                    }
                });
    }
}
