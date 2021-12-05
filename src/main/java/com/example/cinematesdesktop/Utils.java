package com.example.cinematesdesktop;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Utils {

    public static Firestore startDb() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("..\\Cinemates20Desktop\\cinemates20-v1-firebase-adminsdk-um66o-793b38fbbc.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setDatabaseUrl("https://cinemates20-v1.firebaseio.com/")
                .build();

        FirebaseApp app;
        if(FirebaseApp.getApps().isEmpty())
            app = FirebaseApp.initializeApp(options);
        else
            app = FirebaseApp.getInstance();

        return FirestoreClient.getFirestore(app);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
