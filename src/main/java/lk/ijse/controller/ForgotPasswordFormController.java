package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.impl.UserBoImpl;
import lk.ijse.model.UserDTO;
import lk.ijse.dao.custom.impl.UserDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordFormController {
    public TextField txtPhone;
    public TextField txtUserId;
    public TextField txtPassword;
    public Button btnBack;
    public AnchorPane rootNode;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void btnVerifyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String phone = txtPhone.getText();
        if (phone != null) {
            String user = String.valueOf(userBO.findByPhone(phone));
            if (user != null) {
                txtUserId.setText(user);
            }else {
                new Alert(Alert.AlertType.ERROR, "User not found!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Enter phone number!").show();
        }
    }

    public void btnChangeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String userId = txtUserId.getText();
        String password = txtPassword.getText();
        if (userId!=null && password!=null) {
            UserDTO userDTO = new UserDTO(userId, password);
            if (userBO.update(userDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Password changed successfully!").show();
                navigateToLogin();
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to change password!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Enter user id and password!").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        navigateToLogin();
    }

    public void navigateToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
            Stage stage = (Stage) rootNode.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login Form");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
