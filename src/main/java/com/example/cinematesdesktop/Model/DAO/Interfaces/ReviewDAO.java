package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface ReviewDAO {
    String getTextReview(String idReported) throws IOException, ExecutionException, InterruptedException;
    int getTotalReviews(Date date) throws IOException, ExecutionException, InterruptedException;
    void changeStateReview(String idReported) throws IOException;
}
