package lk.ijse.dao.custom;

import javafx.scene.control.Alert;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.entity.Product;
import lk.ijse.model.OrderProductDetailDTO;
import lk.ijse.model.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {
    public boolean deleteDetails(String productId) throws SQLException, ClassNotFoundException;

    public boolean updateQTY(List<OrderProductDetail> odList) throws SQLException, ClassNotFoundException;

    public LocalDate getProductExpirationDate(String productId) throws SQLException, ClassNotFoundException;

    public List<Product> getExpiringProducts(LocalDate thresholdDate) throws SQLException, ClassNotFoundException;

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException;
}
