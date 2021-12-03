package com.example.cinematesdesktop.Controller;

import com.example.cinematesdesktop.Model.DAO.DAOFactory;
import com.example.cinematesdesktop.Model.DAO.Interfaces.CommentDAO;
import com.example.cinematesdesktop.Model.DAO.Interfaces.NotificationDAO;
import com.example.cinematesdesktop.Model.DAO.Interfaces.ReportDAO;
import com.example.cinematesdesktop.Model.DAO.Interfaces.ReviewDAO;
import com.example.cinematesdesktop.Model.Report;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ReportController implements Initializable {

    @FXML private TableView<Report> reportsTable;
    @FXML private TableColumn<Report, String> author;
    @FXML private TableColumn<Report, List<String>> reporters;
    @FXML private TableColumn<Report, String> item;
    @FXML private TableColumn<Report, Date> dateAndTime;
    @FXML private TextArea textArea;
    @FXML private Button rejectButton, confirmButton;

    public ReportController(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> reportsTable.refresh());

        ReportDAO reportDAO = DAOFactory.getReportDAO("firebase");

        try {
            populateTable(reportDAO.getReports());
        } catch (IOException | ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        onClick();

    }

    public void populateTable(List<Report> reportList){
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        reporters.setCellValueFactory(new PropertyValueFactory<>("reporters"));
        item.setCellValueFactory(new PropertyValueFactory<>("typeReported"));
        dateAndTime.setCellValueFactory(new PropertyValueFactory<>("dateAndTime"));

        reportsTable.getItems().setAll(reportList);
        author.setSortType(TableColumn.SortType.ASCENDING);
        dateAndTime.setSortType(TableColumn.SortType.DESCENDING);
    }

    public void onClick() {

        reportsTable.setRowFactory(reportTableView -> {
            TableRow<Report> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {

                    Report rowData = row.getItem();

                    if(rowData.getTypeReported().equals("review")) {
                        ReviewDAO reviewDAO = DAOFactory.getReviewDao("firebase");
                        try {
                            textArea.setText(reviewDAO.getTextReview(rowData.getIdReported()));
                        } catch (IOException | ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        CommentDAO commentDAO = DAOFactory.getCommentDAO("firebase");
                        try {
                            textArea.setText(commentDAO.getTextComment(rowData.getIdReported()));
                        } catch (IOException | InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }

                    rejectButton.setDisable(false);
                    rejectButton.setOnMouseClicked(mouseEvent -> {
                        try {
                            onClickRejectionButton(rowData, reportTableView);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    confirmButton.setDisable(false);
                    confirmButton.setOnMouseClicked(mouseEvent -> {
                        try {
                            onClickConfirmButton(rowData, reportTableView);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }
            });
            return row;
        });
    }

    private void onClickConfirmButton(Report rowData, TableView<Report> reportTableView) throws IOException {
        displaySuccessDialog("You have accepted the report. Your decision will be sent to the users");

        NotificationDAO notificationDAO = DAOFactory.getNotificationDAO("Firebase");
        // send notification to the author
        notificationDAO.sendNotification(rowData.getAuthor(), true, "author", rowData.getTypeReported());
        // send notification to all reporters
        for(String s : rowData.getReporters())
            notificationDAO.sendNotification(s, true, "reporter", rowData.getTypeReported());

        ReportDAO reportDAO = DAOFactory.getReportDAO("Firebase");
        reportDAO.deleteReport(rowData.getIdReport());

        //refresh table and textarea
        reportTableView.getItems().remove(rowData);
        textArea.setText("");
    }

    private void onClickRejectionButton(Report rowData, TableView<Report> reportTableView) throws IOException {
        displaySuccessDialog("You have rejected the report. Your decision will be sent to the users");

        NotificationDAO notificationDAO = DAOFactory.getNotificationDAO("Firebase");
        // send notification to the author
        notificationDAO.sendNotification(rowData.getAuthor(), false, "author", rowData.getTypeReported());
        // send notification to all reporters
        for (String s : rowData.getReporters())
            notificationDAO.sendNotification(s, false, "reporter", rowData.getTypeReported());

        ReportDAO reportDAO = DAOFactory.getReportDAO("Firebase");
        reportDAO.deleteReport(rowData.getIdReport());

        //update counters of items
        if(rowData.getTypeReported().equals("review")){
            ReviewDAO reviewDAO = DAOFactory.getReviewDao("firebase");
            reviewDAO.changeStateReview(rowData.getIdReported());
        } else {
            CommentDAO commentDAO = DAOFactory.getCommentDAO("firebase");
            commentDAO.changeStateContent(rowData.getIdReported());
        }

        //refresh table and textarea
        reportTableView.getItems().remove(rowData);
        textArea.setText("");
    }

    private void displaySuccessDialog(String headerText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(headerText);

        ImageView icon = new ImageView("/images/successIcon.png");
        icon.setFitHeight(35);
        icon.setFitWidth(35);
        alert.getDialogPane().setGraphic(icon);

        alert.showAndWait();
    }

    public void refreshTable(ActionEvent actionEvent) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        populateTable(DAOFactory.getReportDAO("firebase").getReports());
    }

    public void goToStatistic(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../statistics.fxml")));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setTitle("Statistics");
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

