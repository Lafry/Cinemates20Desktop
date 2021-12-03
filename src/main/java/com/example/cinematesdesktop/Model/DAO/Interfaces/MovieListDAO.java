package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface MovieListDAO {
    int getTotalMovieLists(Date date) throws IOException, ExecutionException, InterruptedException;

}
