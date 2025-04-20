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
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapistDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.TherapistTm;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapistBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.TherapistBoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
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
    private ComboBox<?> cmbProgrmmrId;

    @FXML
    private Label llblName;

    @FXML
    private TableColumn<TherapistTm, String> clmTherapistName;

    @FXML
    private TableColumn<TherapistTm, String> cmlAddress;

    @FXML
    private TableColumn<TherapistTm, String> cmlContactNumber;

    @FXML
    private TableColumn<TherapistTm, Integer> cmlTherpistAge;

    @FXML
    private TableView<TherapistTm> tblTherapist;

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


    TherapistBo therapistBo = new TherapistBoImpl();

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        clmTherapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        cmlTherpistAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        cmlContactNumber.setCellValueFactory(new PropertyValueFactory<>("therapistPhone"));
        cmlAddress.setCellValueFactory(new PropertyValueFactory<>("therapistAddress"));

        try {
            loadTable();
            loadTherapistNextId();
        }catch (Exception e){
            e.printStackTrace();
        }

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(therpistAncohpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

    }

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        refreshPage();
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        String ID = txtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = therapistBo.delete(ID);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Therapist...!").show();
            }
        }

    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        String therapistId = txtId.getText();
        String therapistName = txtName.getText();
        String therapistAddress = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String phone = txtPhone.getText();
        String therapyProgrammeId = (String) cmbProgrmmrId.getValue();

        TherapistDto therapistDto = new TherapistDto(therapistId, therapistName,therapistAddress, age, phone, therapyProgrammeId);
        boolean isSaved = therapistBo.save( therapistDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Therapist Saved successfully").show();
            refreshPage();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Therapist Not Saved Please try Again").show();
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        String therapistId = txtId.getText();
        String therapistName = txtName.getText();
        String therapistAddress = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String phone = txtPhone.getText();
        String therapyProgrammeId = String.valueOf(cmbProgrmmrId.getValue());

        TherapistDto therapistDto = new TherapistDto(therapistId, therapistName,therapistAddress, age, phone,therapyProgrammeId);
        boolean isSaved = therapistBo. update( therapistDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Therapist Saved successfully").show();
            refreshPage();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Therapist Not Saved Please try Again").show();
        }
    }

    private void loadTable() throws SQLException, IOException {
        ArrayList<TherapistDto> therapistDtos = (ArrayList<TherapistDto>) therapistBo.getAll();
        ObservableList<TherapistTm> therapistTms = FXCollections.observableArrayList();

        for (TherapistDto therapistDto : therapistDtos) {
            TherapistTm therapistTm = new TherapistTm(
                    therapistDto.getTherapistId(),
                    therapistDto.getTherapistName(),
                    therapistDto.getAge(),
                    therapistDto.getTherapistPhone(),
                    therapistDto.getTherapistAddress(),
                    therapistDto.getTherapistProgrammeId()
            );
            therapistTms.add(therapistTm);
        }
        tblTherapist.setItems(therapistTms);
    }


    private void refreshPage() throws SQLException, IOException {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtPhone.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        btnClear.setDisable(true);
        loadTable();
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        TherapistTm therapistTm = tblTherapist.getSelectionModel().getSelectedItem();
        if (therapistTm != null) {
            txtId.setText(therapistTm.getTherapistId());
            txtName.setText(therapistTm.getTherapistName());
            txtAge.setText(String.valueOf(therapistTm.getAge()));
            txtAddress.setText(therapistTm.getTherapistAddress());
            txtPhone.setText(therapistTm.getTherapistPhone());

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
        }
    }

    private void loadTherapistNextId () throws SQLException, IOException {
        String nextTherapistId = therapistBo.getNextId();
        txtId.setText(nextTherapistId);
    }

}
