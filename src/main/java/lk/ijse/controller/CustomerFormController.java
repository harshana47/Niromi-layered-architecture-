package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.model.CustomerDTO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class
CustomerFormController {

    public Button btnBack;
    public AnchorPane rootNode;
    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<CustomerDTO> tblCustomers;

    @FXML
    private TableColumn<CustomerDTO, String> colCustomerId;

    @FXML
    private TableColumn<CustomerDTO, String> colName;

    @FXML
    private TableColumn<CustomerDTO, String> colEmail;

    @FXML
    private TableColumn<CustomerDTO, String> colPhone;

    private ObservableList<CustomerDTO> customerDTOList = FXCollections.observableArrayList();

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    public void initialize() throws ClassNotFoundException {
        tblCustomers.setItems(customerDTOList);
        loadCustomers(); // Load customers into the table view

        // Initialize table columns
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void loadCustomers() throws ClassNotFoundException {
        try {
            customerDTOList.clear();
            customerDTOList.addAll(customerBO.load());
            tblCustomers.setItems(customerDTOList);
            tblCustomers.refresh();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error loading customers: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        CustomerDTO customerDTO = new CustomerDTO(customerId, name, email, phone); // Passing null for address

        try {
            if (isValied()) {
                boolean saved = customerBO.save(customerDTO);
                if (saved) {
                    loadCustomers(); // Refresh data in TableView
                    clearFields();
                    showAlert(Alert.AlertType.CONFIRMATION, "Customer saved successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed to save customer!");
                }
            }
            } catch(SQLException e){
                showAlert(Alert.AlertType.ERROR, "Error saving customer: " + e.getMessage());
            } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        CustomerDTO selectedCustomerDTO = tblCustomers.getSelectionModel().getSelectedItem();
        if (selectedCustomerDTO != null) {
            boolean deleted = customerBO.delete(selectedCustomerDTO.getCustomerId());
            if (deleted) {
                customerDTOList.remove(selectedCustomerDTO); // Update TableView by removing the deleted customer
                showAlert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to delete customer!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Please select a customer to delete!");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtCustomerId != null) {
            String customerId = txtCustomerId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();

            CustomerDTO updatedCustomerDTO = new CustomerDTO(customerId, name, email, phone);

            boolean updated = customerBO.update(updatedCustomerDTO);
            if (updated) {
                loadCustomers();
                clearFields();
                showAlert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to update customer!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Please select a customer to update!");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        try {
            CustomerDTO customerDTO = customerBO.findById(customerId);
            if (customerDTO != null) {
                txtName.setText(customerDTO.getName());
                txtEmail.setText(customerDTO.getEmail());
                txtPhone.setText(customerDTO.getPhone());
            } else {
                showAlert(Alert.AlertType.ERROR, "Customer not found!");
                clearFields();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error searching customer: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtCustomerId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orderForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Order Controller");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ID, txtCustomerId);
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtName);
    }

    public void txtCustomerEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.EMAIL, txtEmail);
    }

    public void txtCustomerPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtPhone);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ID,txtCustomerId)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.PHONE,txtPhone)) return false;
        return true;
    }
}
