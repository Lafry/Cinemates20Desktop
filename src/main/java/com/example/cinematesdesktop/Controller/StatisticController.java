package com.example.cinematesdesktop.Controller;

import com.example.cinematesdesktop.Model.DAO.DAOFactory;
import com.example.cinematesdesktop.Model.DAO.Interfaces.CommentDAO;
import com.example.cinematesdesktop.Model.DAO.Interfaces.NotificationDAO;
import com.example.cinematesdesktop.Model.DAO.Interfaces.ReportDAO;
import com.example.cinematesdesktop.Model.DAO.Interfaces.ReviewDAO;
import com.example.cinematesdesktop.Model.Report;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class StatisticController implements Initializable {

    @FXML private Button removeDate;
    @FXML private BarChart<?,?> chart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private DatePicker datePicker;
    int totUsers, totComments, totFeed, totUsersOnline, totReview, totMovieList;

    public StatisticController(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getStatistics();
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            startChart();
        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        datePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });
    }

    private void getStatistics() throws IOException, ExecutionException, InterruptedException {
        //set the start of the current day
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);

        totUsers = DAOFactory.getUserDAO("firebase").getTotalUsers(null);
        totComments = DAOFactory.getCommentDAO("firebase").getTotalComments(null);
        totFeed = DAOFactory.getFeedDAO("firebase").getTotalFeeds(null);
        totUsersOnline = DAOFactory.getUserDAO("firebase").getTotalUsersOnline(calendar.getTime());
        totReview = DAOFactory.getReviewDao("firebase").getTotalReviews(null);
        totMovieList = DAOFactory.getMovieListDAO("firebase").getTotalMovieLists(null);
    }

    private void startChart() throws ExecutionException, InterruptedException, IOException {
        XYChart.Series set1 = new XYChart.Series();

        set1.setName("total stats");
        set1.getData().add(new XYChart.Data<>("users", totUsers));
        set1.getData().add(new XYChart.Data<>("users online today", totUsersOnline));
        set1.getData().add(new XYChart.Data<>("reviews", totReview));
        set1.getData().add(new XYChart.Data<>("comments", totComments));
        set1.getData().add(new XYChart.Data<>("personal lists", totMovieList));
        set1.getData().add(new XYChart.Data<>("news on the feed", totFeed));
        x.setCategories(FXCollections.observableArrayList("users", "users online today", "reviews","comments", "personal lists", "news on the feed"));
        chart.getData().addAll(set1);
    }

    public void getDate() throws IOException, ExecutionException, InterruptedException {
        if(datePicker.getValue()!=null) {
            XYChart.Series set2 = new XYChart.Series();

            set2.setName(datePicker.getValue() + " 's stats");
            set2.getData().add(new XYChart.Data<>("users", DAOFactory.getUserDAO("firebase")
                    .getTotalUsers(Date.from(datePicker.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))));
            set2.getData().add(new XYChart.Data<>("users online today", DAOFactory.getUserDAO("firebase")
                    .getTotalUsersOnline(Date.from(datePicker.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))));
            set2.getData().add(new XYChart.Data<>("reviews", DAOFactory.getReviewDao("firebase")
                    .getTotalReviews(Date.from(datePicker.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))));
            set2.getData().add(new XYChart.Data<>("comments", DAOFactory.getCommentDAO("firebase")
                    .getTotalComments(Date.from(datePicker.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))));
            set2.getData().add(new XYChart.Data<>("personal lists", DAOFactory.getMovieListDAO("firebase")
                    .getTotalMovieLists(Date.from(datePicker.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))));
            set2.getData().add(new XYChart.Data<>("news on the feed", DAOFactory.getFeedDAO("firebase")
                    .getTotalFeeds(Date.from(datePicker.getValue().atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))));
            chart.getData().addAll(set2);
            removeDate.setDisable(false);
        }
    }

    public void onClearChartClicked() throws IOException, ExecutionException, InterruptedException {
        chart.getData().clear();
        datePicker.setValue(null);
        startChart();
        removeDate.setDisable(true);
    }

    public void goToReports(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../reports.fxml")));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Reports");
            appStage.setScene(scene);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../login.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("com/example/cinematesdesktop/style.css");
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Hello!");
            appStage.setScene(scene);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}