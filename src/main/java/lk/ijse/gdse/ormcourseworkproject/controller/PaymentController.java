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
import lk.ijse.gdse.ormcourseworkproject.Dto.PaymentDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapyProgrammeDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.PatientTm;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.PaymentTm;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PatientBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PaymentBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapyProgrammeBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.PatientBoImpl;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.PaymentBoImpl;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.TherapyProgrammeBoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Button btnClear;


    @FXML
    private Button btnSave;


    @FXML
    private TableColumn<?, ?> clmBalanace;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmPatientId;

    @FXML
    private TableColumn<?, ?> clmPayement;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableColumn<?, ?> clmTottal;

    @FXML
    private ComboBox<String> cmbMethod;

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private ComboBox<String> cmbTherapyProgramme;

    @FXML
    private AnchorPane payementAnchorpane;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtTotal;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblProgrmme;

    @FXML
    private TextField txtpaymentId;

    PaymentBo paymentBo = new PaymentBoImpl();
    PatientBo patientBo = new PatientBoImpl();
    TherapyProgrammeBo therapyProgrammeBo = new TherapyProgrammeBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        clmPayement.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        clmBalanace.setCellValueFactory(new PropertyValueFactory<>("balance"));
        clmTottal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("cashPrice"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(payementAnchorpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadPatientIDs();
            loadNextId();
            loadMethods();
            loadTable();
            loadProgrammesId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }
//
//    @FXML
//    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException {
//        String ID = txtpaymentId.getText();
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
//        Optional<ButtonType> optionalButtonType = alert.showAndWait();
//
//        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
//
//            boolean isDelete = patientBo.delete(ID);
//            if (isDelete) {
//                loadTable();
//                new Alert(Alert.AlertType.INFORMATION, "Payment deleted...!").show();
//
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to delete Payment...!").show();
//            }
//        }
//    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException {
        String PaymentId = txtpaymentId.getText();
        String paymentMethod = String.valueOf(cmbMethod.getValue());
        double cashPrice = Double.parseDouble(txtPrice.getText());
        double balance = Double.parseDouble(txtBalance.getText());
        double totalAmount = Double.parseDouble(txtTotal.getText());
        String patientId = String.valueOf(cmbPatientId.getValue());


        PaymentDto paymentDTO = new PaymentDto(PaymentId,paymentMethod,cashPrice,balance,totalAmount,patientId);
        boolean isSave = paymentBo.save(paymentDTO);

        if (isSave) {
//            refreshPage();  //
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "Payment Saved SUCCESSFULLY").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "PLEASE TRY AGAIN ").show();
        }
    }

//    @FXML
//    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException {
//        String PaymentId = txtpaymentId.getText();
//        String paymentMethod = String.valueOf(cmbMethod.getValue());
//        double cashPrice = Double.parseDouble(txtPrice.getText());
//        double balance = Double.parseDouble(txtBalance.getText());
//        double totalAmount = Double.parseDouble(txtTotal.getText());
//        String patientId = String.valueOf(cmbPatientId.getValue());
//
//
//        PaymentDto paymentDTO = new PaymentDto(PaymentId,paymentMethod,cashPrice,balance,totalAmount,patientId);
//        boolean isUpdate = paymentBo.save(paymentDTO);
//
//        if (isUpdate) {
////            refreshPage();  //
//            loadTable();
//            new Alert(Alert.AlertType.INFORMATION, "Payment Saved SUCCESSFULLY").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "PLEASE TRY AGAIN ").show();
//        }
//    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        PaymentTm paymentTm = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTm != null) {
            txtpaymentId.setText(paymentTm.getPatientId());
            txtTotal.setText(String.valueOf(paymentTm.getTotalAmount()));
            txtBalance.setText(String.valueOf(paymentTm.getBalance()));
            txtPrice.setText(String.valueOf(paymentTm.getCashPrice()));
            cmbPatientId.setValue(paymentTm.getPatientId());
            cmbMethod.setValue(paymentTm.getPaymentMethod());

            btnSave.setDisable(true);
        }

    }

    @FXML
    void cmbOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedID = cmbPatientId.getValue();
        PatientDto patientDTO = patientBo.findBy(selectedID);

        if (patientDTO != null) {
            lblPatientName.setText(patientDTO.getName());
        }
    }


    @FXML
    void programmeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedID = cmbTherapyProgramme.getValue();
        TherapyProgrammeDto therapyProgrammeDto = therapyProgrammeBo.findBy(selectedID);

        if (therapyProgrammeDto != null) {
            lblPatientName.setText(String.valueOf(therapyProgrammeDto.getTherapyPrice()));
        }
    }


    private void loadTable() throws SQLException, IOException {
        ArrayList<PaymentDto> paymentDtos = (ArrayList<PaymentDto>) paymentBo.getAll();
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDto.getPaymentId(),
                    paymentDto.getPaymentMethod(),
                    paymentDto.getBalance(),
                    paymentDto.getTotalAmount(),
                    paymentDto.getBalance(),
                    paymentDto.getPatientId()
            );
            paymentTms.add(paymentTm);
        }
        tblPayment.setItems(paymentTms);
    }

    private void loadPatientIDs() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<String> patientIds = patientBo.getAllPatientIDs();
        cmbPatientId.getItems().addAll(patientIds);
    }

    private void loadMethods(){
        String[]methods = {"Cash Payment", "Card Payment"};
        cmbMethod.getItems().addAll(methods);
    }

    private void loadNextId() throws SQLException, IOException {
        String nextPaymentId = paymentBo.getNextId();
        txtpaymentId.setText(nextPaymentId);
    }

    private void loadProgrammesId() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<String> programmeIds = therapyProgrammeBo.getAllTherapyProgrammeId();
        cmbTherapyProgramme.getItems().addAll(programmeIds);
    }
}
