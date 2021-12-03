package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.DAO.Interfaces.ReviewDAO;
import com.example.cinematesdesktop.Model.Review;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReviewDAO_Firebase implements ReviewDAO {

    @Override
    public String getTextReview(String idReported) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();

        Review review = new Review();
        ApiFuture<QuerySnapshot> query = db.collection("reviews")
                .whereEqualTo("idReview", idReported)
                .get();
        // ...
        // query.get() blocks on response
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            review = document.toObject(Review.class);
        }
        return review.getTextReview();
    }

    @Override
    public int getTotalReviews(Date date) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();
        if(date==null){
            ApiFuture<QuerySnapshot> query = db.collection("reviews")
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }else{
            ApiFuture<QuerySnapshot> query = db.collection("reviews")
                    .whereGreaterThan("dateAndTime", date)
                    .whereLessThan("dateAndTime", Utils.addDays(date, 1))
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }
    }

    @Override
    public void changeStateReview(String idReported) throws IOException {
        Firestore db = Utils.startDb();

        db.collection("reviews").document(idReported).update("counterForLanguage", 0);
        db.collection("reviews").document(idReported).update("isInappropriate", false);
    }
}
