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
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.PatientTm;
import lk.ijse.gdse.ormcourseworkproject.Dto.Tm.UserTm;
import lk.ijse.gdse.ormcourseworkproject.Dto.UserDto;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.UserBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.UserBoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    UserBo userBo = new UserBoImpl();

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmID;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPassword;

    @FXML
    private TableColumn<?, ?> clmRole;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private AnchorPane userAnchorpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmID.setCellValueFactory(new PropertyValueFactory<>("userNameId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        clmRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(userAnchorpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadCmb();
            loadTable();
            refreshPage();
            loadPatientNextId();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadPatientNextId () throws SQLException, IOException {
        String nextUserId = userBo.getNextId();
        txtId.setText(nextUserId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, IOException {
        refreshPage();
    }

    private void refreshPage() throws SQLException, IOException {
        txtId.setText("");
        txtName.setText("");
        txtPassword.setText("");
        cmbRole.setValue("");

        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        btnClear.setDisable(false);
        loadTable();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException {
        String ID = txtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = userBo.delete(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "User deleted...!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete User...!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException {
        String userNameId = txtId.getText();
        String userName = txtName.getText();
        String userPassword = txtPassword.getText();
        String userRole = cmbRole.getValue().toString();

        UserDto userDto = new UserDto(userNameId, userName, userPassword, userRole);
        boolean isSaved = userBo.save( userDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"User Saved successfully").show();
            refreshPage();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"User Not Saved Please try Again").show();
        }

    }

    private void loadTable() throws SQLException, IOException {
        ArrayList<UserDto> userDtos = (ArrayList<UserDto>) userBo.getAll();
        ObservableList<UserTm> userTms = FXCollections.observableArrayList();

        for (UserDto userDto : userDtos) {
            UserTm userTm = new UserTm(
                    userDto.getUserNameId(),
                    userDto.getUserName(),
                    userDto.getUserPassword(),
                    userDto.getUserRole()
            );
            userTms.add(userTm);
        }
        tblUser.setItems(userTms);
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException {
        String userNameId = txtId.getText();
        String userName = txtName.getText();
        String userPassword = txtPassword.getText();
        String userRole = cmbRole.getValue().toString();

        UserDto userDto = new UserDto(userNameId, userName, userPassword, userRole);
        boolean isSaved = userBo.update( userDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"User update successfully").show();
            refreshPage();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"User Not update Please try Again").show();
        }
    }

    @FXML
    void tblOnClick(MouseEvent event) {
        UserTm userTm = tblUser.getSelectionModel().getSelectedItem();
        if (userTm != null) {
            txtId.setText(userTm.getUserNameId());
            txtName.setText(userTm.getUserName());
            txtPassword.setText(userTm.getUserPassword());
            cmbRole.setValue(userTm.getUserRole());

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
        }
    }


    private void loadCmb(){
        String[] role = {"Admin", "User"};
        cmbRole.getItems().addAll(role);
    }


}
