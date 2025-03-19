package lk.ijse.gdse.ormcourseworkproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LoginController {

    @FXML
    private AnchorPane LoginAnchorPane;

    @FXML
    private Button loginBut;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Pane userNamePasswordAnchorePane;

    @FXML
    private TextField txtPassword01;

    public boolean isPasswordVisible = false;


    @FXML
    void eyeHiddenPassword(MouseEvent event) {
        if (isPasswordVisible) {
            txtPassword.setText(txtPassword01.getText());

            txtPassword01.setVisible(false);
            txtPassword01.setManaged(false);
            txtPassword.setVisible(true);
            txtPassword.setManaged(true);
        } else {
            txtPassword01.setText(txtPassword.getText());

            txtPassword01.setVisible(true);
            txtPassword01.setManaged(true);
            txtPassword.setVisible(false);
            txtPassword.setManaged(false);// layout ekenma ain krnwa
        }

        isPasswordVisible = !isPasswordVisible;

    }


    @FXML
    void loginOnAction(ActionEvent event) {
        try {
            String username = txtName.getText();
            String password = txtPassword.getText();

            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
            txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String passwordPattern = "^[A-Za-z0-9]+$";

            boolean isValidName = username.matches(namePattern);
            boolean isValidPassword = password.matches(passwordPattern);

            if (!isValidName) {
                System.out.println(txtName.getStyle());
                txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidPassword) {
                System.out.println(txtPassword.getStyle());
                txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: red;");
            }

            if (isValidName && isValidPassword) {
                if ((username.equals("admin") && password.equals("1234")||
                        (username.equals("user") && password.equals("1234")))){
                    if (username.equals("admin")) {
                        HomePageController.isAdmin = true;
                    }else {
                        HomePageController.isAdmin = false;
                    }
                    LoginAnchorPane.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
                    LoginAnchorPane.getChildren().add(load);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid username or password ").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "LoginPage Not Found").show();
        }
    }

}
