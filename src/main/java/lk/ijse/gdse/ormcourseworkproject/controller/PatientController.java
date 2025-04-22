package lk.ijse.gdse.ormcourseworkproject.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.ormcourseworkproject.Dto.PatientDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.PatientTm;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PatientBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.PatientBoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<String, PatientTm> clmContactNumber;

    @FXML
    private TableColumn<String, PatientTm> clmDate;

    @FXML
    private TableColumn<String, PatientTm> clmHistory;

    @FXML
    private TableColumn<String, PatientTm> clmPatientId;

    @FXML
    private TableColumn<String, PatientTm> clmPatientName;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private AnchorPane patientAnchorpane;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtMedicalHistory;

    @FXML
    private TextField txtPAddress;

    @FXML
    private TextField txtPId;

    @FXML
    private TextField txtPName;

    @FXML
    private TextField txtPhone;

    PatientBo patientBo = new PatientBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        clmPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        clmHistory.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("birthday"));


        TranslateTransition slider = new TranslateTransition();
        slider.setNode(patientAnchorpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            refreshPage();
            loadTable();
            loadPatientNextId();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private void loadPatientNextId () throws SQLException, IOException {
        String nextPatientId = patientBo.getNextId();
        txtPId.setText(nextPatientId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, IOException {
       refreshPage();

    }

    private void refreshPage() throws SQLException, IOException {
        txtPId.setText("");
        txtPName.setText("");
        txtMedicalHistory.setText("");
        txtDate.setValue(LocalDate.now());
        txtPhone.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        btnClear.setDisable(false);
        loadTable();


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException {
        String ID = txtPId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = patientBo.delete(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted...!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Patient...!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException {
        String patientId = txtPId.getText();
        String name = txtPName.getText();
        Date birthday = Date.valueOf(txtDate.getValue());
        String medicalHistory = txtMedicalHistory.getText();
        String phone = txtPhone.getText();

        PatientDto patientDTO = new PatientDto(patientId, name, birthday, phone, medicalHistory);
        boolean isSaved = patientBo.save( patientDTO);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Patient Saved successfully").show();
            loadTable();
                refreshPage();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Patient Not Saved Please try Again").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException {
        String patientId = txtPId.getText();
        String name = txtPName.getText();
        Date birthday = Date.valueOf(txtDate.getValue());
        String medicalHistory = txtMedicalHistory.getText();
        String phone = txtPhone.getText();

        PatientDto patientDTO = new PatientDto(patientId, name, birthday, phone, medicalHistory);
        boolean isSaved = patientBo.update( patientDTO);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Patient update successfully").show();
            refreshPage();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Patient Not Saved Please try Again").show();
        }
    }

    private void loadTable() throws SQLException, IOException {
        ArrayList<PatientDto> patientDTOS = (ArrayList<PatientDto>) patientBo.getAll();
        ObservableList<PatientTm> patientTMs = FXCollections.observableArrayList();

        for (PatientDto patientDTO : patientDTOS) {
            PatientTm patientTM = new PatientTm(
                    patientDTO.getPatientId(),
                    patientDTO.getName(),
                    patientDTO.getBirthday(),
                    patientDTO.getContactNumber(),
                    patientDTO.getMedicalHistory()
            );
            patientTMs.add(patientTM);
        }
        tblPatient.setItems(patientTMs);
    }


    @FXML
    void tblOnMouseClick(MouseEvent event) {
        PatientTm patientTM = tblPatient.getSelectionModel().getSelectedItem();
        if (patientTM != null) {
            txtPId.setText(patientTM.getPatientId());
            txtPName.setText(patientTM.getName());
            txtPhone.setText(patientTM.getContactNumber());
            txtDate.setValue(patientTM.getBirthday().toLocalDate());
            txtMedicalHistory.setText(patientTM.getMedicalHistory());

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
        }
    }
}
