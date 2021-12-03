package com.example.cinematesdesktop.Model.DAO.Implements;

import com.example.cinematesdesktop.Utils;
import com.example.cinematesdesktop.Model.DAO.Interfaces.ReportDAO;
import com.example.cinematesdesktop.Model.Report;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ReportDAO_Firebase implements ReportDAO {

    @Override
    public void deleteReport(String idReport) throws IOException {
        Firestore db = Utils.startDb();

        db.collection("reports").document(idReport).delete();
    }

    @Override
    public List<Report> getReports() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Firestore db = Utils.startDb();

        List<Report> reportList = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = db.collection("reports")
                .whereGreaterThan("reportCounter", 2)
                .get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Report report = document.toObject(Report.class);
            reportList.add(report);
        }
        return reportList;
    }

}
