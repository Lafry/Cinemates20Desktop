package com.example.cinematesdesktop.Model.DAO.Interfaces;

import java.io.IOException;

public interface NotificationDAO {
    void sendNotification(String user, boolean resultReport, String typeUser, String typeItem) throws IOException;
}
