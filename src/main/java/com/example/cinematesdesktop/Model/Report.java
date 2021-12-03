package com.example.cinematesdesktop.Model;


import java.util.Date;
import java.util.List;

public class Report {
    private String author, idReport, idReported, typeReported;
    private List<String> reporters;
    private Date dateAndTime;
    private int reportCounter;

    public Report() {
    }

    public Report(String author, String idReport, String idReported, String typeReported, List<String> reporters, Date dateAndTime, int reportCounter) {
        this.author = author;
        this.idReport = idReport;
        this.idReported = idReported;
        this.typeReported = typeReported;
        this.reporters = reporters;
        this.dateAndTime = dateAndTime;
        this.reportCounter = reportCounter;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdReport() {
        return idReport;
    }

    public void setIdReport(String idReport) {
        this.idReport = idReport;
    }

    public String getIdReported() {
        return idReported;
    }

    public void setIdReported(String idReported) {
        this.idReported = idReported;
    }

    public String getTypeReported() {
        return typeReported;
    }

    public void setTypeReported(String typeReported) {
        this.typeReported = typeReported;
    }

    public List<String> getReporters() {
        return reporters;
    }

    public void setReporters(List<String> reporters) {
        this.reporters = reporters;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getReportCounter() {
        return reportCounter;
    }

    public void setReportCounter(int reportCounter) {
        this.reportCounter = reportCounter;
    }

    @Override
    public String toString() {
        return "Report{" +
                "author='" + author + '\'' +
                ", idReport='" + idReport + '\'' +
                ", idReported='" + idReported + '\'' +
                ", typeReported='" + typeReported + '\'' +
                ", reporters=" + reporters +
                ", dateAndTime=" + dateAndTime +
                ", reportCounter=" + reportCounter +
                '}';
    }
}
