package lk.ijse.controller;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProductBO;
import lk.ijse.bo.custom.SupplierProductDetailBO;
import lk.ijse.bo.impl.ProductBoImpl;
import lk.ijse.bo.impl.SupplierProductBoImpl;
import lk.ijse.dao.DAOFactory;
import lk.ijse.model.ProductDTO;
import lk.ijse.model.SupplierProductDetailDTO;
import lk.ijse.dao.custom.impl.ProductDAOImpl;
import lk.ijse.dao.custom.impl.SupplierProductDAOImpl;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ProductFormController {

    @FXML
    private ComboBox<String> cmbSupplier;

    @FXML
    private TableColumn<ProductDTO, String> colSupplier;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ProductDTO, String> colProductId;

    @FXML
    private TableColumn<ProductDTO, String> colName;

    @FXML
    private TableColumn<ProductDTO, String> colExpireDate;

    @FXML
    private TableColumn<ProductDTO, Double> colPrice;

    @FXML
    private TableColumn<ProductDTO, Integer> colQtyOnHand;

    @FXML
    private TableColumn<ProductDTO, String> colEmployeeId;

    @FXML
    private TableColumn<ProductDTO, String> colPromotionId;

    @FXML
    private TableView<ProductDTO> tblProducts;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtExpireDate;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtPromotionId;

    SupplierDAOImpl supplierDAO = (SupplierDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);


    private ObservableList<ProductDTO> productDTOList = FXCollections.observableArrayList();

    SupplierProductDetailBO supplierProductBo = new SupplierProductBoImpl();
    ProductBO productBO = new ProductBoImpl();

    public ProductFormController() {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws ClassNotFoundException {
        String productId = txtProductId.getText();
        String name = txtName.getText();
        String expireDate = txtExpireDate.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQuantity.getText());
        String employeeId = txtEmployeeId.getText();
        String promoId = txtPromotionId.getText();
        String supplierName = cmbSupplier.getValue();

        ProductDTO productDTO = new ProductDTO(productId, name, expireDate, price, qtyOnHand, employeeId, promoId, supplierName);

        try {
            if(isValid()) {
                boolean isAddedProduct = productBO.save(productDTO);
                if (isAddedProduct) {
                    tblProducts.getItems().add(productDTO);

                    SupplierDAOImpl supplierDAOImpl = new SupplierDAOImpl();

                    String supplierId = supplierDAOImpl.getSupplierIdByName(supplierName);


                    SupplierProductDetailDTO supplierProductDetailDTO = SupplierProductDetailDTO.builder()
                            .productId(productId)
                            .supplierId(supplierId)
                            .build();

                    //supplierProductDetails
                    boolean isAddedSupplierProductDetail = supplierProductBo.save(supplierProductDetailDTO);
                    if (isAddedSupplierProductDetail) {

                        colSupplier.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(supplierName));

                        clearFields();
                    } else {
                        System.out.println("Failed to add supplier product detail!");
                    }
                } else {
                    System.out.println("Failed to add product!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ProductDTO selectedProductDTO = tblProducts.getSelectionModel().getSelectedItem();
        if (selectedProductDTO != null) {
            boolean isDeleted =productBO.deleteDetails(selectedProductDTO.getProductId());
            if (isDeleted) {
                tblProducts.getItems().remove(selectedProductDTO);
                productBO.delete(selectedProductDTO.getProductId());
            } else {
                System.out.println("Failed to delete product!");
            }
        } else {
            System.out.println("Please select a product to delete!");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = txtProductId.getText();
        ProductDTO productDTO = productBO.search(productId);
        if (productDTO != null) {
            txtName.setText(productDTO.getName());
            txtExpireDate.setText(productDTO.getExpireDate());
            txtPrice.setText(String.valueOf(productDTO.getPrice()));
            txtQuantity.setText(String.valueOf(productDTO.getQtyOnHand()));
            txtEmployeeId.setText(productDTO.getEmployeeId());
            txtPromotionId.setText(productDTO.getPromoId());
        } else {
            System.out.println("Product not found!");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = txtProductId.getText();
        String name = txtName.getText();
        String expireDate = txtExpireDate.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQuantity.getText());
        String employeeId = txtEmployeeId.getText();
        String promoId = txtPromotionId.getText();
        String supplierName = cmbSupplier.getValue();

        ProductDTO productDTO = new ProductDTO(productId, name, expireDate, price, qtyOnHand, employeeId, promoId, supplierName);

        boolean isUpdated = productBO.update(productDTO);
        if (isUpdated) {
            // Update product in TableView
            int selectedIndex = tblProducts.getSelectionModel().getSelectedIndex();
            tblProducts.getItems().set(selectedIndex, productDTO);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Failed to update product!");
        }
    }

    @FXML
    public void initialize() {
        colProductId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProductId()));
        colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        colExpireDate.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getExpireDate()));
        colPrice.setCellValueFactory(cellData -> new ReadOnlyDoubleWrapper(cellData.getValue().getPrice()).asObject());
        colQtyOnHand.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQtyOnHand()).asObject());
        colEmployeeId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmployeeId()));
        colPromotionId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPromoId()));
        colSupplier.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSupplierName()));

        try {
            List<String> supplierNames = supplierDAO.getAllSupplierNames();
            cmbSupplier.setItems(FXCollections.observableArrayList(supplierNames));

            loadProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProducts() throws SQLException, ClassNotFoundException {
        productDTOList.clear();
        productDTOList.addAll(productBO.getAllProducts());
        tblProducts.setItems(productDTOList);
    }


    private void clearFields() {
        txtProductId.clear();
        txtName.clear();
        txtExpireDate.clear();
        txtPrice.clear();
        txtQuantity.clear();
        txtEmployeeId.clear();
        txtPromotionId.clear();
    }

    public void txtProductIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.TWOID,txtProductId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName);
    }

    public void txtExpireDateOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.DATE,txtExpireDate);
    }

    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.AMOUNT,txtPrice);
    }

    public void txtQuantityOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.QUANTITY,txtQuantity);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TWOID,txtProductId)) return false;;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,txtExpireDate)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.AMOUNT,txtPrice)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.QUANTITY,txtQuantity)) return false;
        return true;
    }
}
