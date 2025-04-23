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
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapyProgrammeDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.PatientTm;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.TherapistProgrammeTm;
import lk.ijse.gdse.ormcourseworkproject.bo.BoFactory;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapyProgrammeBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.TherapyProgrammeBoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProgrammeController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFees;

    @FXML
    private TableColumn<?, ?> clmProgrammeName;

    @FXML
    private TableColumn<?, ?> clmProgrmmeId;

    @FXML
    private TableColumn<?, ?> clmTheraphistId;

    @FXML
    private TableView<TherapistProgrammeTm> tblProgrmme;

    @FXML
    private ComboBox<String> cmbTherapyProgrammeName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFees;

    @FXML
    private TextField txtProgrammeId;

    @FXML
    private TextField txtProgrammeName;

//    @FXML
//    private TextField txtTheraphistId;

    @FXML
    private AnchorPane programmeAnchorPane;

    TherapyProgrammeBo therapyProgrammeBo = (TherapyProgrammeBo) BoFactory.getInstance().getBo(BoFactory.boType.THERAPYPROGRAMME);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(programmeAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();


        clmProgrmmeId.setCellValueFactory(new PropertyValueFactory<>("therapyProgrammeId"));
        clmProgrammeName.setCellValueFactory(new PropertyValueFactory<>("therapyProgrammeName"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("therapyDuration"));
        clmFees.setCellValueFactory(new PropertyValueFactory<>("therapyPrice"));

        try {
            loadTable();
            refreshPage();
            loadProgrammes();
//            loadTherapyProgrammeId();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        refreshPage();
    }

//    private void loadTherapyProgrammeId () throws SQLException, IOException {
//        String nextTherapyProgrammeId = therapyProgrammeBo.getNextId();
//        txtProgrammeId.setText(nextTherapyProgrammeId);
//    }

    private void loadProgrammes() {
        String[] programmes = {"Cognitive Behavioral Therapy", "Mindfulness-Based Stress Reduction", "Dialectical Behavior Therapy", "Group Therapy Sessions", "Family Counseling"};
        cmbTherapyProgrammeName.getItems().addAll(programmes);
    }

    private void refreshPage() throws SQLException, IOException {
        txtProgrammeId.setText("");
        cmbTherapyProgrammeName.setValue("");
        txtDuration.setText("");
        txtFees.setText("");


        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        btnClear.setDisable(false);
        loadTable();
    }


    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        String ID = txtProgrammeId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = therapyProgrammeBo.delete(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Therapy Programme deleted...!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Therapy Programme...!").show();
            }
        }
    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws SQLException, IOException {

        String programmeId = txtProgrammeId.getText();
        String programmeName = cmbTherapyProgrammeName.getValue();
        String programmeDuration = txtDuration.getText();
        double price = Double.parseDouble(txtFees.getText());

        TherapyProgrammeDto therapyProgrammeDto = new TherapyProgrammeDto(programmeId, programmeName, programmeDuration, price);
        boolean isSaved = therapyProgrammeBo.save(therapyProgrammeDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapy Programme Saved successfully").show();
            refreshPage();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapy Programme Not Saved Please try Again").show();
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        String programmeId = txtProgrammeId.getText();
        String programmeName = cmbTherapyProgrammeName.getValue();
        String programmeDuration = txtDuration.getText();
        double price = Double.parseDouble(txtFees.getText());

        TherapyProgrammeDto therapyProgrammeDto = new TherapyProgrammeDto(programmeId, programmeName, programmeDuration, price);
        boolean isSaved = therapyProgrammeBo.update(therapyProgrammeDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapy Programme update successfully").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapy Programme Not update Please try Again").show();
        }
    }

    private void loadTable() throws SQLException, IOException {
        ArrayList<TherapyProgrammeDto> therapyProgrammeDtos = (ArrayList<TherapyProgrammeDto>) therapyProgrammeBo.getAll();
        ObservableList<TherapistProgrammeTm> therapistProgrammeTms = FXCollections.observableArrayList();

        for (TherapyProgrammeDto therapyProgrammeDto : therapyProgrammeDtos) {
            TherapistProgrammeTm therapistProgrammeTm = new TherapistProgrammeTm(
                    therapyProgrammeDto.getTherapyProgrammeId(),
                    therapyProgrammeDto.getTherapyProgrammeName(),
                    therapyProgrammeDto.getTherapyDuration(),
                    therapyProgrammeDto.getTherapyPrice()
            );
            therapistProgrammeTms.add(therapistProgrammeTm);
        }
        tblProgrmme.setItems(therapistProgrammeTms);
    }


    @FXML
    void tblOnMouseClick(MouseEvent event) {
        TherapistProgrammeTm therapistProgrammeTm = tblProgrmme.getSelectionModel().getSelectedItem();
        if (therapistProgrammeTm != null) {
            txtProgrammeId.setText(therapistProgrammeTm.getTherapyProgrammeId());
            cmbTherapyProgrammeName.setValue(therapistProgrammeTm.getTherapyProgrammeName());
            txtDuration.setText(therapistProgrammeTm.getTherapyDuration());
            txtFees.setText(String.valueOf(therapistProgrammeTm.getTherapyPrice()));

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
        }
    }
}
