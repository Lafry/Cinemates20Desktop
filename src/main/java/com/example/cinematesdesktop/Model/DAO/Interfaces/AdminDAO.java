package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface AdminDAO {
    boolean isAdminExists(String ID, String password) throws ExecutionException, InterruptedException, IOException;
}
