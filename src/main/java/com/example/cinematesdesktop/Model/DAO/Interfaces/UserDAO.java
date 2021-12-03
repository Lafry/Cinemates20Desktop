package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface UserDAO {
    int getTotalUsers(Date date) throws IOException, ExecutionException, InterruptedException;
    int getTotalUsersOnline(Date date) throws IOException, ExecutionException, InterruptedException;
}
