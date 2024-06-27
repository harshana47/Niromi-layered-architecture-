package lk.ijse.controller;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.*;
import lk.ijse.bo.impl.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.db.DbConnection;
import lk.ijse.model.*;
import lk.ijse.tdm.CartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {

    private final List<OrderProductDetailDTO> productDetails = new ArrayList<>();
    public Label lblTotal;
    public Button btnPrintBill;
    public ComboBox txtPaymentId;
    public Button btnAddCus;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblCurrentDate;
    @FXML
    private Button btnCheckout;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn<CartTm, String> colProductId;
    @FXML
    private TableColumn<CartTm, Integer> colQuantity;
    @FXML
    private TableColumn<CartTm, Double> colPrice;
    @FXML
    private TableColumn<CartTm, String> colDiscount;
    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtProductId;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private ComboBox txtPromoId;
    @FXML
    private Label lblCustomer;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblExpireDiscountStatus;
    @FXML
    private TableView<CartTm> tblOrders;
    @FXML
    public AnchorPane node;

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);
    PromotionBO promotionBO = (PromotionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROMOTION);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public OrderFormController() throws SQLException {
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        generateOrderId();
        lblCurrentDate.setText(LocalDate.now().toString());

        txtQuantity.setOnKeyPressed(event -> {
            if (event.getCode().isDigitKey() || event.getCode() == KeyCode.BACK_SPACE) {
                try {
                    handleQuantityChanged();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (event.getCode() == KeyCode.ENTER) {
                txtPromoId.requestFocus();
            }
        });

        txtCustomerId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    handleCustomerIdEntered();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Initialize TableView columns
        colProductId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProductId()));
        colQuantity.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQty()).asObject());

        ObservableList<PaymentDTO> paymentMethods = FXCollections.observableArrayList(paymentBO.getAllPaymentMethods());
        txtPaymentId.setItems(paymentMethods);
        try {
            ObservableList<String> promotions = FXCollections.observableArrayList(promotionBO.getAllPromoNames());
            txtPromoId.setItems(promotions);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colPrice.setCellValueFactory(cellData -> {
            CartTm cartItem = cellData.getValue();
            try {
                return new ReadOnlyDoubleWrapper(calculateGeneratedPrice(cartItem.getProductId(), cartItem.getQty())).asObject();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        colDiscount.setCellValueFactory(cellData -> {
            CartTm cartItem = cellData.getValue();
            String productId = cartItem.getProductId();
            String promoId = (String) txtPromoId.getValue();
            try {
                String promo = promotionBO.findPromotionByName(promoId);
                PromotionDTO promotionDTO = promotionBO.findById(promo);
                if (promotionDTO != null) {
                    return new ReadOnlyStringWrapper(promotionDTO.getDiscountPercentage() + "%");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return new ReadOnlyStringWrapper("");
        });
    }

    private double calculateGeneratedPrice(String productId, int quantity) throws SQLException, ClassNotFoundException {
        ProductDTO productDTO = productBO.findById(productId);
        if (productDTO != null) {
            BigDecimal price = BigDecimal.valueOf(productDTO.getPrice()).multiply(BigDecimal.valueOf(quantity));

            if ("given".equals(lblExpireDiscountStatus.getText())) {
                price = price.multiply(BigDecimal.valueOf(0.5));
            }

            return price.doubleValue();
        }
        return 0.0;
    }


    private void generateOrderId() throws ClassNotFoundException {
        try {
            String nextOrderId = orderBO.getNextOrderId();
            txtOrderId.setText(nextOrderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleQuantityChanged() throws ClassNotFoundException, SQLException {
        calculatePrice();
        calculateExpireStatus();

    }

    @FXML
    private void calculatePrice() throws SQLException, ClassNotFoundException {
        try {
            String productId = txtProductId.getText();
            String quantityText = txtQuantity.getText();

            if (productId.isEmpty() || quantityText.isEmpty()) {
                lblPrice.setText("Please enter Product ID and Quantity");
                return;
            }

            int quantity = Integer.parseInt(quantityText);
            ProductDTO productDTO = productBO.findById(productId);

            if (productDTO != null) {
                BigDecimal price = BigDecimal.valueOf(productDTO.getPrice()).multiply(BigDecimal.valueOf(quantity));

                if ("given".equals(lblExpireDiscountStatus.getText())) {
                    price = price.multiply(BigDecimal.valueOf(0.5));
                }

                lblPrice.setText(price.toString());
            } else {
                lblPrice.setText("Invalid Product ID");
            }

        } catch (NumberFormatException e) {
            lblPrice.setText("Error calculating price");
            e.printStackTrace();
        }
    }

    private void calculateExpireStatus() throws ClassNotFoundException {
        try {
            String productId = txtProductId.getText();
            ProductDTO productDTO = productBO.findById(productId);
            if (productDTO != null) {
                LocalDate currentDate = LocalDate.now();
                LocalDate expirationDate = productBO.getProductExpirationDate(productId);
                if (expirationDate != null && expirationDate.isBefore(currentDate.plusMonths(5))) {
                    lblExpireDiscountStatus.setText("given");
                } else {
                    lblExpireDiscountStatus.setText("not");
                }
            } else {
                lblExpireDiscountStatus.setText("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleCustomerIdEntered() throws ClassNotFoundException {
        try {
            String phone = txtCustomerId.getText();
            if (!phone.isEmpty()) {
                CustomerDTO customerDTO = customerBO.findByPhone(phone);
                if (customerDTO != null) {
                    lblCustomer.setText(customerDTO.getName());
                    txtCustomerId.setText(customerDTO.getCustomerId());
                } else {
                    lblCustomer.setText("Customer not found");
                }
            } else {
                lblCustomer.setText("Please enter Customer ID");
            }
        } catch (SQLException e) {
            lblCustomer.setText("Error fetching customer details");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddOnAction() throws ClassNotFoundException {
        try {
            String orderId = txtOrderId.getText();
            String productId = txtProductId.getText();
            String quantityText = txtQuantity.getText();
            String promoId = (String) txtPromoId.getValue();

            if (orderId.isEmpty() || productId.isEmpty() || quantityText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            int quantity = Integer.parseInt(quantityText);
            ProductDTO productDTO = productBO.findById(productId);
            if (productDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid Product ID").show();
                return;
            }

            BigDecimal price = BigDecimal.valueOf(productDTO.getPrice()).multiply(BigDecimal.valueOf(quantity));

            if ("given".equals(lblExpireDiscountStatus.getText())) {
                price = price.multiply(BigDecimal.valueOf(0.5)); // 50% discount
            }

            if (!promoId.isEmpty()) {
                if (promoId != null) {
                    promoId = promotionBO.findPromotionByName(promoId);
                }
                PromotionDTO promotionDTO = promotionBO.findById(promoId);
                if (promotionDTO != null) {
                    BigDecimal discountPercentage = BigDecimal.valueOf(Double.parseDouble(promotionDTO.getDiscountPercentage()));
                    BigDecimal discountAmount = price.multiply(discountPercentage.divide(BigDecimal.valueOf(100)));
                    price = price.subtract(discountAmount);
                }
            }

            if (quantity > productDTO.getQtyOnHand()) {
                new Alert(Alert.AlertType.ERROR, "not enough items left. only " + productDTO.getQtyOnHand() + " items left").show();
                return;
            }
            CartTm cartItem = new CartTm(productId, quantity, price.doubleValue());
            if (isValid()) {
                obList.add(cartItem);
                tblOrders.setItems(obList);

                updateTotal();

                txtProductId.clear();
                txtQuantity.clear();
                lblPrice.setText("");
                txtProductId.requestFocus();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input").show();
            e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error adding item: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    private void updateTotal() {
        double total = obList.stream().mapToDouble(item -> item.getPrice()).sum();
        lblTotal.setText(String.format("%.2f", total));
    }

    @FXML
    private void btnRemoveOnAction(ActionEvent actionEvent) {
        CartTm selectedItem = tblOrders.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            obList.remove(selectedItem);
            updateTotal();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select an item to remove").show();
        }
    }

    @FXML
    private void btnClearOnAction(ActionEvent actionEvent) {
        txtPromoId.setValue(null);
        lblCustomer.setText("");
        lblExpireDiscountStatus.setText("");
        obList.clear();
        tblOrders.getItems().clear();
        obList.clear();
        tblOrders.getItems().clear();
    }

    @FXML
    private boolean btnCheckoutOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String orderId = txtOrderId.getText();
        String customerId = txtCustomerId.getText();
        String total = lblTotal.getText();
        String promoName = (String) txtPromoId.getValue();
        String promoId = null;
        if (promoName != null) {
            promoId = promotionBO.findPromotionByName(promoName);
        }
        String expireStatus = lblExpireDiscountStatus.getText();
        String paymentMethod = txtPaymentId.getValue().toString();
        try {
            String paymentId = paymentBO.getPaymentIdByMethod(paymentMethod);

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(orderId);
            orderDTO.setCustomerId(customerId);
            orderDTO.setPaymentId(paymentId);
            orderDTO.setPromoId(promoId);
            orderDTO.setExpireDiscountStatus(expireStatus);
            orderDTO.setTotalAmount(Double.valueOf(total));

            LocalDate orderDate = LocalDate.now();
            orderDTO.setOrderDate(orderDate);

            List<OrderProductDetailDTO> list = obList.stream().map(product -> {
                return OrderProductDetailDTO.builder()
                        .orderId(orderId)
                        .productId(product.getProductId())
                        .qty(product.getQty())
                        .total(orderDTO.getTotalAmount())
                        .orderDate(orderDate)
                        .build();
            }).toList();
            orderDTO.setOrderProductDetailDTOList(list);

            boolean isOrderSaved = orderBO.save(orderDTO);
            new Alert(Alert.AlertType.INFORMATION,
                    isOrderSaved ? "Order saved successfully"
                            : "Ooops something went wrong").show();
            generateOrderId();

            obList.clear();
            tblOrders.getItems().clear();

            txtPromoId.setValue(null);
            txtCustomerId.clear();
            lblPrice.setText("");
            lblTotal.setText("");
            lblExpireDiscountStatus.setText("");

            connection.setAutoCommit(false);
            if (!isOrderSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            OrderDetailDAOImpl orderDetailRepo = new OrderDetailDAOImpl();
            boolean isOrderDetailSaved = orderDetailRepo.saveOrderProductDetail(orderDTO.getOrderProductDetailDTOList());
            if (!isOrderDetailSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            ProductDAOImpl productRepo = new ProductDAOImpl();
            boolean isQtyUpdated = productRepo.updateQTY(orderDTO.getOrderProductDetailDTOList());
            if (!isQtyUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }

    }


    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelectionForm.fxml"));
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
    @FXML
    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (event.getSource() instanceof TextField) {
                TextField textField = (TextField) event.getSource();
                switch (textField.getId()) {
                    case "txtProductId":
                        txtQuantity.requestFocus();
                        break;
                    case "txtQuantity":
                        break;
                    case "txtPromoId":
                        txtCustomerId.requestFocus();
                        break;
                    case "txtCustomerId":
                        break;
                }
            }
        }
    }

    public void txtOderQuantityOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.COUNT, txtQuantity);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.Util.TextField.COUNT, txtQuantity)) ;
        return true;
    }

    public void btnPrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/Niromi.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void btnAddCusOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        this.node.getChildren().clear();
        this.node.getChildren().add(root);
    }
}
