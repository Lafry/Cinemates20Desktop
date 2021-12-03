package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.DAO.Interfaces.FeedDAO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FeedDAO_Firebase implements FeedDAO {
    @Override
    public int getTotalFeeds(Date date) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();
        if(date==null){
            ApiFuture<QuerySnapshot> query = db.collection("feed")
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }else{
            ApiFuture<QuerySnapshot> query = db.collection("feed")
                    .whereGreaterThan("dateAndTime", date)
                    .whereLessThan("dateAndTime", Utils.addDays(date, 1))
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }
    }
}
