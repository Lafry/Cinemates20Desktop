package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface FeedDAO {
    int getTotalFeeds(Date date) throws IOException, ExecutionException, InterruptedException;
}
