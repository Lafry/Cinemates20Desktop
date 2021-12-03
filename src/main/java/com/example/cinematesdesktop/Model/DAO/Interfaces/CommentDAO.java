package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface CommentDAO {
    String getTextComment(String idReported) throws IOException, ExecutionException, InterruptedException;
    int getTotalComments(Date date) throws IOException, ExecutionException, InterruptedException;
    void changeStateContent(String idReported) throws IOException;
}
