package lk.ijse.gdse.ormcourseworkproject.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.ormcourseworkproject.Dto.PatientDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapyProgrammeDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapySessionDto;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PatientBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapyProgrammeBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapySessionBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.PatientBoImpl;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.TherapyProgrammeBoImpl;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.TherapySessionBoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmPatientId;

    @FXML
    private TableColumn<?, ?> clmProgrammeId;

    @FXML
    private TableColumn<?, ?> clmSessionDate;

    @FXML
    private TableColumn<?, ?> clmSessionName;

    @FXML
    private TableColumn<?, ?> clmSessionTime;

    @FXML
    private TableColumn<?, ?> clmSesstionId;

    @FXML
    private ComboBox<String> cmbPId;

    @FXML
    private ComboBox<String> cmbPrId;

    @FXML
    private AnchorPane sessionAnchorpane;

    @FXML
    private TextField txtSId;

    @FXML
    private DatePicker txtSdate;

    @FXML
    private TextField txtSname;

    @FXML
    private TextField txtTime;

    TherapySessionBo therapySessionBo = new TherapySessionBoImpl();
    PatientBo patientBo = new PatientBoImpl();
    TherapyProgrammeBo therapyProgrammeBo = new TherapyProgrammeBoImpl();

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
//        try {
        String sessionId = txtSId.getText();
        String sessionName = txtSname.getText();
        Date sessionDate = Date.valueOf(txtSdate.getValue());
        String sessionTime = txtTime.getText();
        String patientId = cmbPId.getValue();
        String programmeId = cmbPrId.getValue();

        TherapySessionDto therapySessionDto = new TherapySessionDto(sessionId, sessionName, sessionDate, sessionTime, patientId,programmeId);
        boolean isSaved = therapySessionBo.save(therapySessionDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "therapy Session Saved successfully").show();
//                loadTable();
//                refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "therapy Session Not Saved Please try Again").show();
        }
//        }catch (SQLException | IOException e){
        new Alert(Alert.AlertType.ERROR, "not found ").show();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String sessionId = txtSId.getText();
        String sessionName = txtSname.getText();
        Date sessionDate = Date.valueOf(txtSdate.getValue());
        String sessionTime = txtTime.getText();
        String patientId = cmbPId.getValue();
        String programmeId = cmbPrId.getValue();

        TherapySessionDto therapySessionDto = new TherapySessionDto(sessionId, sessionName, sessionDate, sessionTime, patientId,programmeId);
        boolean isSaved = therapySessionBo.update(therapySessionDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "therapy Session Saved successfully").show();
//                loadTable();
//                refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "therapy Session Not Saved Please try Again").show();
        }
//        }catch (SQLException | IOException e){
        new Alert(Alert.AlertType.ERROR, "not found ").show();

    }

    private void loadSessionNextId (){
        try{
            String nextSessionId = therapySessionBo.getNextId();
            txtSId.setText(nextSessionId);
        }catch (SQLException | IOException e){
            new Alert(Alert.AlertType.ERROR, "Load Fail Patient Id").show();
        }
    }

    private void loadPatientIDs() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<String> patientIds = patientBo.getAllPatientIDs();
        cmbPId.getItems().addAll(patientIds);
    }

    private void loadProgrammesId() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<String> programmeIds = therapyProgrammeBo.getAllTherapyProgrammeId();
        cmbPrId.getItems().addAll(programmeIds);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(sessionAnchorpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadPatientIDs();
            loadProgrammesId();
            loadSessionNextId();
        }catch (Exception e){

        }
    }
}
