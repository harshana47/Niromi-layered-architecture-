package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProductDAO;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.entity.Product;
import lk.ijse.model.OrderProductDetailDTO;
import lk.ijse.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    public boolean save(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO product (productId, name, expireDate, price, qtyOnHand, employeeId, promoId, supplierName) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",entity.getProductId(),entity.getName(),entity.getExpireDate(),entity.getPrice(),entity.getQtyOnHand(),entity.getEmployeeId(),entity.getPromoId(),entity.getSupplierName());
    }

    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM product WHERE productId=?",productId);
    }


    public Product search(String productId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product WHERE productId=?",productId);
        if (resultSet.next()) {
            return new Product(
                    resultSet.getString("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("expireDate"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("qtyOnHand"),
                    resultSet.getString("employeeId"),
                    resultSet.getString("promoId"),
                    resultSet.getString("supplierName")
            );
        }
        return null;
    }

    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET name=?, expireDate=?, price=?, qtyOnHand=?, employeeId=?, promoId=?, supplierName=? WHERE productId=?",entity.getName(),entity.getExpireDate(),entity.getPrice(),entity.getQtyOnHand(),entity.getEmployeeId(),entity.getPromoId(),entity.getSupplierName(),entity.getProductId());
    }
    public boolean updateQTY(List<OrderProductDetail> odList) throws SQLException, ClassNotFoundException {
        boolean test = false;
        for (OrderProductDetail product : odList) {
            test = SQLUtil.execute("UPDATE product SET qtyOnHand = qtyOnHand - ? WHERE productId = ?",product.getQty(),product.getProductId());
        }
        return test;
    }
    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM product");
        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
            productList.add(new Product(
                    rs.getString("productId"),
                    rs.getString("name"),
                    rs.getString("expireDate"),
                    rs.getDouble("price"),
                    rs.getInt("qtyOnHand"),
                    rs.getString("employeeId"),
                    rs.getString("promoId"),
                    rs.getString("supplierName")
            ));
        }
        return productList;
    }



    public LocalDate getProductExpirationDate(String productId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT expireDate FROM product WHERE productId=?",productId);
        if (resultSet.next()){
            return resultSet.getDate("expireDate").toLocalDate();
        }
        return null;
    }

    public Product findById(String productId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product WHERE productId=?",productId);
        if (resultSet.next()){
            return new Product(
                    resultSet.getString("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("expireDate"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("qtyOnHand"),
                    resultSet.getString("employeeId"),
                    resultSet.getString("promoId"),
                    resultSet.getString("supplierName")
            );
        }
        return null;
    }

    @Override
    public Product findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {
        return null;
    }

    public List<Product> getExpiringProducts(LocalDate thresholdDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product WHERE expireDate <= ?",thresholdDate);
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product(
                    resultSet.getString("productId"),
                    resultSet.getString("name"),
                    resultSet.getString("expireDate"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("qtyOnHand"),
                    resultSet.getString("employeeId"),
                    resultSet.getString("promoId"),
                    resultSet.getString("supplierName")
            );
            productList.add(product);
        }
        return productList;
    }
}