package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Controller.LoginAdminController;
import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.Admin;
import com.example.cinematesdesktop.Model.DAO.Interfaces.AdminDAO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AdminDAO_Firebase implements AdminDAO {

    @Override
    public boolean isAdminExists(String ID, String password) throws ExecutionException, InterruptedException, IOException {
        Firestore db = Utils.startDb();

        // asynchronously retrieve all users
        Admin admin;
        ApiFuture<QuerySnapshot> query = db.collection("admin")
                .whereEqualTo("ID", ID)
                .whereEqualTo("password", password)
                .get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        return documents.isEmpty();
    }
}
