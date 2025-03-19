package lk.ijse.gdse.ormcourseworkproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public static boolean isAdmin;


    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnPationt;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnPrograme;

    @FXML
    private Button btnSesstion;

    @FXML
    private Button btnTheraphist;

    @FXML
    private ImageView imgPrograme;

    @FXML
    private ImageView imgTheraphist;

    @FXML
    private AnchorPane loadAnchorpane;

    @FXML
    private AnchorPane mainAnchorpane;



    @FXML
    void logoutOnAction(ActionEvent event) {

    }

    @FXML
    void pationtOnAction(ActionEvent event) {
        navigateTo("/View/patient.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {

    }

    @FXML
    void programeOnAction(ActionEvent event) {

    }

    @FXML
    void sessionOnAction(ActionEvent event) {

    }

    @FXML
    void theraphistOnAction(ActionEvent event) {
        navigateTo("/View/therapist.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin){
            btnTheraphist.setDisable(false);
            btnSesstion.setDisable(false);
            btnPrograme.setDisable(false);
            btnPationt.setDisable(false);
            btnPayment.setDisable(false);
        }else {
//            btnTheraphist.setVisible(false);
//            btnPrograme.setVisible(false);
//            btnPationt.setVisible(true);
//            btnPayment.setVisible(true);
//            btnSesstion.setVisible(true);
            btnTheraphist.setDisable(true);
            imgTheraphist.setVisible(true);
            btnSesstion.setDisable(false);
            btnPrograme.setDisable(true);
            imgPrograme.setVisible(true);
            btnPationt.setDisable(false);
            btnPayment.setDisable(false);
        }
    }

    private void navigateTo(String fxmlPath) {
        try {
            loadAnchorpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane loadedPane = loader.load();
            loadAnchorpane.getChildren().add(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load view: " + fxmlPath);
        }
        System.out.println("Loading FXML: " + fxmlPath);
    }
}
