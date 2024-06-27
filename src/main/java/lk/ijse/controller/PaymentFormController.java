package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.impl.PaymentBoImpl;
import lk.ijse.model.PaymentDTO;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;

import java.sql.SQLException;

public class PaymentFormController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPayId;

    @FXML
    private TableColumn<?, ?> colPayMethod;

    @FXML
    private Label lblMethodId;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TableView<PaymentDTO> tblPayment;

    @FXML
    private TextField txtMethod;
    @FXML
    private TextField txtPayId;

    private ObservableList<PaymentDTO> paymentDTOList = FXCollections.observableArrayList();

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public PaymentFormController() {

    }

    @FXML
    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentId = txtPayId.getText();
        String method = txtMethod.getText();

        PaymentDTO paymentDTO = new PaymentDTO(paymentId, method);
        if (isValid()) {
            paymentBO.save(paymentDTO);
            loadPaymentDataIntoTable();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PaymentDTO selectedPaymentDTO = tblPayment.getSelectionModel().getSelectedItem();

        if (selectedPaymentDTO != null) {
            paymentBO.delete(String.valueOf(selectedPaymentDTO));
            loadPaymentDataIntoTable();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PaymentDTO selectedPaymentDTO = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedPaymentDTO != null) {
            String newMethod = txtMethod.getText();
            selectedPaymentDTO.setMethod(newMethod);

            paymentBO.update(selectedPaymentDTO);
            loadPaymentDataIntoTable();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentId = txtPayId.getText();

        PaymentDTO paymentDTO = paymentBO.search(paymentId);
        if (paymentDTO != null) {
            tblPayment.getItems().clear();
            tblPayment.getItems().add(paymentDTO);
        } else {
            showAlert(Alert.AlertType.ERROR, "Payment Search", "Payment with ID " + paymentId + " not found.");
        }
    }

    private void loadProducts() throws SQLException, ClassNotFoundException {
        paymentDTOList.clear();
        paymentDTOList.addAll(paymentBO.getAllPaymentMethods());
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPayMethod.setCellValueFactory(new PropertyValueFactory<>("method"));

        tblPayment.setItems(paymentDTOList);

        loadPaymentDataIntoTable();
    }

    private void loadPaymentDataIntoTable() throws SQLException, ClassNotFoundException {
        paymentDTOList.clear();
        paymentDTOList.addAll(paymentBO.getAllPaymentMethods());
    }

    public void txtPaymentMethodOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtMethod);
    }

    public void txtPaymentIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.TWOID, txtPayId);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtMethod)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TWOID, txtPayId)) return false;
        return true;
    }
}
