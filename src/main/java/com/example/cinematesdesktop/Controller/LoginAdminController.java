package com.example.cinematesdesktop.Controller;

import com.example.cinematesdesktop.Model.DAO.DAOFactory;
import com.example.cinematesdesktop.Model.DAO.Interfaces.AdminDAO;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class LoginAdminController implements Initializable {
    @FXML private TextField idField, passwordField;
    @FXML private Button loginButton;
    @FXML private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BooleanBinding booleanBind = idField.textProperty().isEmpty()
                .or(passwordField.textProperty().isEmpty());
        loginButton.disableProperty().bind(booleanBind);
        progressIndicator.setVisible(false);
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        if(!idField.getText().isEmpty() & !passwordField.getText().isEmpty()) {
            //progressIndicator.setVisible(true);

            AdminDAO adminDAO = DAOFactory.getAdminDAO("firebase");
            if(!adminDAO.isAdminExists(idField.getText(), passwordField.getText())) {
                switchScene(event);
            }else
                displayDialogError();
        }
    }

    public void switchScene(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../reports.fxml")));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setTitle("Reports");
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayDialogError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("Wrong username or password");
        alert.showAndWait();
    }

}