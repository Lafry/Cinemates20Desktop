package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.Comment;
import com.example.cinematesdesktop.Model.DAO.Interfaces.CommentDAO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CommentDAO_Firebase implements CommentDAO {

    @Override
    public String getTextComment(String idReported) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();

        Comment comment = new Comment();

        ApiFuture<QuerySnapshot> query = db.collection("comments")
                .whereEqualTo("idComment", idReported)
                .get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            comment = document.toObject(Comment.class);
        }
        return comment.getTextComment();
    }

    @Override
    public int getTotalComments(Date date) throws IOException, ExecutionException, InterruptedException {
        Firestore db = Utils.startDb();
        if(date==null){
            ApiFuture<QuerySnapshot> query = db.collection("comments")
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }else{
            ApiFuture<QuerySnapshot> query = db.collection("comments")
                    .whereGreaterThan("dateAndTime", date)
                    .whereLessThan("dateAndTime", Utils.addDays(date, 1))
                    .get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
            return documents.size();
        }
    }

    @Override
    public void changeStateContent(String idReported) throws IOException {
        Firestore db = Utils.startDb();

        db.collection("comments").document(idReported).update("counterForLanguage", 0);
        db.collection("comments").document(idReported).update("visible", true);
    }
}
