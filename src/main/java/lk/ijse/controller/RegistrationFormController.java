package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.impl.UserBoImpl;
import lk.ijse.model.UserDTO;
import lk.ijse.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class RegistrationFormController {
    @FXML
    private TableColumn<UserDTO, String> colId;

    @FXML
    private TableColumn<UserDTO, String> colName;

    @FXML
    private TableColumn<UserDTO, String> colPassword;

    @FXML
    private TableColumn<UserDTO, String> colPhone;

    @FXML
    private TableView<UserDTO> tblUsers;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    private ObservableList<UserDTO> userDTOList = FXCollections.observableArrayList();

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    public void initialize() throws ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadUsers();
    }

    private void loadUsers() throws ClassNotFoundException {
        try {
            List<UserDTO> userDTOS = userBO.getAllUsers();
            userDTOList.setAll(userDTOS);
            tblUsers.setItems(userDTOList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading users: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws ClassNotFoundException {
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String phone = txtPhone.getText();

        try {
            UserDTO userDTO = new UserDTO();
            boolean isSaved = userBO.save(userDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error saving user: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String userId = txtUserId.getText();
        if (userId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a user ID to delete.").show();
            return;
        }

        try {
            boolean isDeleted = userBO.delete(userId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully!").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete user. User not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error deleting user: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws ClassNotFoundException {
        String userId = txtUserId.getText();
        if (userId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a user ID to search.").show();
            return;
        }

        try {
            UserDTO userDTO = userBO.findById(userId);
            if (userDTO != null) {
                txtName.setText(userDTO.getName());
                txtPassword.setText(userDTO.getPassword());
                txtPhone.setText(userDTO.getPhone());
            } else {
                new Alert(Alert.AlertType.ERROR, "User not found.").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error searching user: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String phone = txtPhone.getText();

        if (userId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a user ID to update.").show();
            return;
        }

        try {
            UserDTO userDTO = new UserDTO();
            boolean isUpdated = userBO.update(userDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully!").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update user. User not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error updating user: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtUserId.clear();
        txtName.clear();
        txtPassword.clear();
        txtPhone.clear();
    }
}
