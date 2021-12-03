package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.DAO.Interfaces.UserDAO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserDAO_Firebase implements UserDAO {

    @Override
    public int getTotalUsers(Date date) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();
        if(date==null){
            ApiFuture<QuerySnapshot> query = db.collection("users")
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }else{
            ApiFuture<QuerySnapshot> query = db.collection("users")
                    .whereGreaterThan("first_login", date)
                    .whereLessThan("first_login", Utils.addDays(date, 1))
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }
    }

    @Override
    public int getTotalUsersOnline(Date date) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();
        ApiFuture<QuerySnapshot> query = db.collection("users")
                .whereGreaterThan("last_login", date)
                .whereLessThan("last_login", Utils.addDays(date, 1))
                .get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        return documents.size();
    }
}
