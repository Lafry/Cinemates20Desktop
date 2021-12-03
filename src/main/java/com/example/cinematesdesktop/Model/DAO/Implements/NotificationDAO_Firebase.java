package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.DAO.Interfaces.NotificationDAO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NotificationDAO_Firebase implements NotificationDAO {

    @Override
    public void sendNotification(String user, boolean resultReport, String typeUser, String typeItem) throws IOException {
        Firestore db = Utils.startDb();

        // Create a Map to store the data we want to
        Map<String, Object> docData = new HashMap<>();
        docData.put("dateAndTime", new Date());
        docData.put("flag", "unchecked");
        docData.put("typeNotification", "report");
        docData.put("resultReport", resultReport);
        docData.put("userWhoSent", "admin");
        docData.put("userWhoReceived", user);
        docData.put("typeUser", typeUser);
        docData.put("typeItem", typeItem);

        ApiFuture<DocumentReference> future = db.collection("notifications").add(docData);
        // ...//future.get() blocks on response
        try {
            System.out.println("Update time : " + future.get().getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
