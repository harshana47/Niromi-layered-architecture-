package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.ProductBO;
import lk.ijse.bo.impl.EmployeeBoImpl;
import lk.ijse.bo.impl.ProductBoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.model.ProductDTO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.dao.custom.impl.ProductDAOImpl;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ExpireSoonFormController {

    public Button btnMail;
    public AnchorPane rootNode;
    public AnchorPane node;

    @FXML
    private TableColumn<ProductDTO, String> colEmployeeId;

    @FXML
    private TableColumn<ProductDTO, LocalDate> colExpireDate;

    @FXML
    private TableColumn<ProductDTO, String> colName;

    @FXML
    private TableColumn<ProductDTO, Double> colPrice;

    @FXML
    private TableColumn<ProductDTO, String> colProductId;

    @FXML
    private TableColumn<ProductDTO, String> colPromotionId;

    @FXML
    private TableColumn<ProductDTO, Integer> colQtyOnHand;

    @FXML
    private TableColumn<ProductDTO, String> colSupplier;

    @FXML
    private TableView<ProductDTO> tblExpireProducts;

    private final Connection connection;


    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public ExpireSoonFormController() throws SQLException {
        this.connection = DbConnection.getInstance().getConnection();
    }

    @FXML
    void initialize() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colPromotionId.setCellValueFactory(new PropertyValueFactory<>("promoId"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        loadExpireProducts();
    }


    private void loadExpireProducts() {
        try {
            String sql = "SELECT * FROM product WHERE expireDate BETWEEN ? AND ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pst.setDate(2, java.sql.Date.valueOf(LocalDate.now().plusMonths(1)));

            ResultSet rs = pst.executeQuery();
            ObservableList<ProductDTO> productDTOList = FXCollections.observableArrayList();

            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(rs.getString("productId"));
                productDTO.setName(rs.getString("name"));
                productDTO.setExpireDate(rs.getString("expireDate")); // Check column name
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setQtyOnHand(rs.getInt("qtyOnHand"));
                productDTO.setEmployeeId(rs.getString("employeeId"));
                productDTO.setPromoId(rs.getString("promoId"));
                productDTO.setSupplierName(rs.getString("supplierName"));

                productDTOList.add(productDTO);
            }

            tblExpireProducts.setItems(productDTOList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ProductDTO selectedProductDTO = tblExpireProducts.getSelectionModel().getSelectedItem();
        if (selectedProductDTO != null) {
            // Delete product from repository
            boolean isDeleted =productBO.deleteDetails(selectedProductDTO.getProductId());
            if (isDeleted) {
                tblExpireProducts.getItems().remove(selectedProductDTO);
                productBO.delete(selectedProductDTO.getProductId());
            } else {
                System.out.println("Failed to delete product!");
            }
        } else {
            System.out.println("Please select a product to delete!");
        }
    }

    public void btnMailOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        sendEmail();
    }

    public void sendEmail() throws SQLException, ClassNotFoundException {
        try {
            List<String> employeeEmails = employeeBO.getAllEmployeeEmails();
            List<ProductDTO> expiringProductDTOS = productBO.getExpiringProducts(LocalDate.now().plusMonths(2));

            StringBuilder emailBody = new StringBuilder("Hello,\n\nThe following products are expiring within 2 months:\n");
            for (ProductDTO productDTO : expiringProductDTOS) {
                emailBody.append("Product ID: ").append(productDTO.getProductId())
                        .append(", Name: ").append(productDTO.getName()).append("\n");
            }

            String subject = "Expiring Products Notification";
            String encodedEmailBody = URLEncoder.encode(emailBody.toString(), StandardCharsets.UTF_8);
            String encodedSubject = URLEncoder.encode(subject, StandardCharsets.UTF_8);

            StringBuilder emailAddresses = new StringBuilder();
            for (String employeeEmail : employeeEmails) {
                emailAddresses.append(employeeEmail).append(",");
            }
            emailAddresses.deleteCharAt(emailAddresses.length() - 1);

            String url = "https://mail.google.com/mail/?view=cm&fs=1&to=" + emailAddresses.toString() + "&body=" + encodedEmailBody + "&su=" + encodedSubject;

            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            System.out.println("An error occurred: " + e.getLocalizedMessage());
        }
    }

}




