package com.example.cinematesdesktop.Model.DAO.Interfaces;

import com.example.cinematesdesktop.Model.Report;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ReportDAO {
    void deleteReport(String idReport) throws IOException;

    List<Report> getReports() throws IOException, ExecutionException, InterruptedException, TimeoutException;
}
