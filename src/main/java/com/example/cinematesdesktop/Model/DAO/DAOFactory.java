package com.example.cinematesdesktop.Model.DAO;

import com.example.cinematesdesktop.Model.DAO.Implements.*;
import com.example.cinematesdesktop.Model.DAO.Interfaces.*;

import java.util.Locale;

public class DAOFactory {

    public static AdminDAO getAdminDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new AdminDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static UserDAO getUserDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new UserDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static ReportDAO getReportDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new ReportDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static NotificationDAO getNotificationDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new NotificationDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static ReviewDAO getReviewDao(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new ReviewDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static CommentDAO getCommentDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new CommentDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static MovieListDAO getMovieListDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new MovieListsDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public static FeedDAO getFeedDAO(String type){
        switch (type.toLowerCase(Locale.ROOT)){
            case "firebase":
                return new FeedDAO_Firebase();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
