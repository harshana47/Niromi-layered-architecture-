package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.OrderProductDetailDTO;
import lk.ijse.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {

    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String productId) throws SQLException, ClassNotFoundException;

    public ProductDTO search(String productId) throws SQLException, ClassNotFoundException;

    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    public boolean updateQTY(List<OrderProductDetailDTO> odList) throws SQLException, ClassNotFoundException;

    public List<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException;

    public LocalDate getProductExpirationDate(String productId) throws SQLException, ClassNotFoundException;

    public ProductDTO findById(String productId) throws SQLException, ClassNotFoundException;

    public ProductDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<ProductDTO> DTOList) throws SQLException, ClassNotFoundException;

    public List<ProductDTO> getExpiringProducts(LocalDate thresholdDate) throws SQLException, ClassNotFoundException;
}
