package lk.ijse.gdse.ormcourseworkproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PaymentController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

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
    private ComboBox<?> cmbMethod;

    @FXML
    private ComboBox<?> cmbPatientId;

    @FXML
    private ComboBox<?> cmbTherapyProgramme;

    @FXML
    private AnchorPane payementAnchorpane;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtpaymentId;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {

    }

}
