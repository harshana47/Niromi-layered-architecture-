package lk.ijse.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.impl.SupplierBoImpl;
import lk.ijse.model.SupplierDTO;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierFormController {

    public Button btnBack;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplierId;

    @FXML
    private TableColumn<SupplierDTO, String> colName;

    @FXML
    private TableColumn<SupplierDTO, String> colAddress;

    @FXML
    private TableColumn<SupplierDTO, String> colContact;

    @FXML
    private TableColumn<SupplierDTO, String> colEmail;

    @FXML
    private TableView<SupplierDTO> tblSuppliers;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    private ObservableList<SupplierDTO> supplierDTOList;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public SupplierFormController() {
        supplierDTOList = FXCollections.observableArrayList(); // Initialize the ObservableList
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        SupplierDTO supplierDTO = new SupplierDTO(supplierId, name, address, contact, email);

        try {
            boolean isAdded = supplierBO.save(supplierDTO);
            if (isAdded) {
                tblSuppliers.getItems().add(supplierDTO);
                clearFields();
            } else {
                System.out.println("Failed to add supplier to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding supplier: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        SupplierDTO selectedSupplierDTO = tblSuppliers.getSelectionModel().getSelectedItem();
        if (selectedSupplierDTO != null) {
            try {
                boolean isDeleted = supplierBO.delete(selectedSupplierDTO.getSupplierId());
                if (isDeleted) {
                    tblSuppliers.getItems().remove(selectedSupplierDTO);
                } else {
                    System.out.println("Failed to delete supplier from the database.");
                }
            } catch (SQLException e) {
                System.out.println("Error deleting supplier: " + e.getMessage());
            }
        } else {
            System.out.println("Please select a supplier to delete.");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        SupplierDTO selectedSupplierDTO = tblSuppliers.getSelectionModel().getSelectedItem();
        if (selectedSupplierDTO != null) {
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();
            String email = txtEmail.getText();

            selectedSupplierDTO.setName(name);
            selectedSupplierDTO.setAddress(address);
            selectedSupplierDTO.setContact(contact);
            selectedSupplierDTO.setEmail(email);

            try {
                boolean isUpdated = supplierBO.update(selectedSupplierDTO);
                if (isUpdated) {
                    tblSuppliers.refresh(); // Refresh the table view
                    clearFields();
                } else {
                    System.out.println("Failed to update supplier in the database.");
                }
            } catch (SQLException e) {
                System.out.println("Error updating supplier: " + e.getMessage());
            }
        } else {
            System.out.println("Please select a supplier to update.");
        }
    }
    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = txtSupplierId.getText();
        try {
            SupplierDTO supplierDTO = supplierBO.search(id);
            if (supplierDTO != null) {
                txtName.setText(supplierDTO.getName());
                txtAddress.setText(supplierDTO.getAddress());
                txtContact.setText(supplierDTO.getContact());
                txtEmail.setText(supplierDTO.getEmail());
                new Alert(Alert.AlertType.INFORMATION, "Supplier found!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error searching for supplier: " + e.getMessage()).show();
        }
    }



    private void clearFields() {
        txtSupplierId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
    }

    @FXML
    public void initialize() throws ClassNotFoundException {
        try {
            colSupplierId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSupplierId()));
            colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
            colAddress.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAddress()));
            colContact.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getContact()));
            colEmail.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));

            tblSuppliers.setItems(supplierDTOList);
            loadSuppliers();
        } catch (SQLException e) {
            System.out.println("Error initializing: " + e.getMessage());
        }
    }

    private void loadSuppliers() throws SQLException, ClassNotFoundException {
        supplierDTOList.clear();
        supplierDTOList.addAll(supplierBO.getAllSuppliers());
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboardForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Controller");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void txtSupplierIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtSupplierId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtAddress);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.EMAIL,txtEmail);
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE,txtContact);
    }
}
