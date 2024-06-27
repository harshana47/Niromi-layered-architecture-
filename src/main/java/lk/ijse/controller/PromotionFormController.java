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
import lk.ijse.bo.custom.PromotionBO;
import lk.ijse.bo.impl.PromotionBoImpl;
import lk.ijse.model.PromotionDTO;
import lk.ijse.dao.custom.impl.PromotionDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class PromotionFormController {
    public TextField txtPromotionId;
    public TextField txtPromotionName;
    public Button btnBack;
    @FXML
    private TableView<PromotionDTO> tblPromotions;

    @FXML
    private TableColumn<PromotionDTO, String> colPromotionId;

    @FXML
    private TableColumn<PromotionDTO, String> colPromotionName;

    @FXML
    private TableColumn<PromotionDTO, String> colDiscountPercentage;

    @FXML
    private TextField txtDiscountPercentage;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    private ObservableList<PromotionDTO> promotionDTOList = FXCollections.observableArrayList();

    PromotionBO promotionBO = (PromotionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROMOTION);

    @FXML
    void initialize() throws ClassNotFoundException {
        try {
            colPromotionId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPromoId()));
            colPromotionName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPromoName()));
            colDiscountPercentage.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDiscountPercentage()));

            tblPromotions.setItems(promotionDTOList);
            loadPromotions();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error initializing: " + e.getMessage());
        }
    }


    private void loadPromotions() throws SQLException, ClassNotFoundException {
        promotionDTOList.clear();
        promotionDTOList.addAll(promotionBO.getAllPromotions());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String promoId = txtPromotionId.getText();
        String promoName = txtPromotionName.getText();
        String discountPercentage = txtDiscountPercentage.getText();

        PromotionDTO promotionDTO = new PromotionDTO(promoId, promoName, discountPercentage);

        try {
            if (isValid()) {
                boolean saved = promotionBO.save(promotionDTO);
                if (saved) {
                    loadPromotions();
                    clearFields();
                    showAlert(Alert.AlertType.CONFIRMATION, "Promotion saved successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed to save promotion!");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error saving promotion: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String promoId = txtPromotionId.getText();
        String promoName = txtPromotionName.getText();
        String discountPercentage = txtDiscountPercentage.getText();

        PromotionDTO promotionDTO = new PromotionDTO(promoId, promoName, discountPercentage);

        try {
            boolean updated = promotionBO.update(promotionDTO);
            if (updated) {
                loadPromotions();
                clearFields();
                showAlert(Alert.AlertType.CONFIRMATION, "Promotion updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to update promotion!");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error updating promotion: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        PromotionDTO selectedPromotionDTO = tblPromotions.getSelectionModel().getSelectedItem();
        if (selectedPromotionDTO != null) {
            try {
                boolean deleted = promotionBO.delete(selectedPromotionDTO.getPromoId());
                if (deleted) {
                    promotionDTOList.remove(selectedPromotionDTO);
                    showAlert(Alert.AlertType.CONFIRMATION, "Promotion deleted successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed to delete promotion!");
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error deleting promotion: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Please select a promotion to delete!");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws ClassNotFoundException {
        String promoId = txtPromotionId.getText();
        try {
            PromotionDTO promotionDTO = promotionBO.search(promoId);
            if (promotionDTO != null) {
                txtPromotionName.setText(promotionDTO.getPromoName());
                txtDiscountPercentage.setText(promotionDTO.getDiscountPercentage());
            } else {
                showAlert(Alert.AlertType.ERROR, "Promotion not found!");
                clearFields();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error searching promotion: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtPromotionId.clear();
        txtPromotionName.clear();
        txtDiscountPercentage.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
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

    public void txtPromotionIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtPromotionId);
    }

    public void txtPromotionNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtPromotionName);
    }

    public void txtDiscountPercentageOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.COUNT,txtDiscountPercentage);
    }
    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtPromotionId)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtPromotionName)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.COUNT,txtDiscountPercentage)) return false;
        return true;
    }
}
