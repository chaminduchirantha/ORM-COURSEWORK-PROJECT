package lk.ijse.gdse.ormcourseworkproject.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.TherapistTm;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {



    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TherapistTm, String> clmTherapistId;

    @FXML
    private TableColumn<TherapistTm, String> clmTherapistName;

    @FXML
    private TableColumn<TherapistTm, String> cmlAddress;

    @FXML
    private TableColumn<TherapistTm, String> cmlContactNumber;

    @FXML
    private TableColumn<TherapistTm, Integer> cmlTherpistAge;

    @FXML
    private TableView<?> tblTherapist;

    @FXML
    private AnchorPane therpistAncohpane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        clmTherapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        cmlTherpistAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        cmlContactNumber.setCellValueFactory(new PropertyValueFactory<>("therapistPhone"));
        cmlAddress.setCellValueFactory(new PropertyValueFactory<>("therapistAddress"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(therpistAncohpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

    }

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {

    }


}
