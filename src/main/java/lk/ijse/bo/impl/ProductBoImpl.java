package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.ProductBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProductDAO;
import lk.ijse.dao.custom.impl.ProductDAOImpl;
import lk.ijse.model.OrderProductDetailDTO;
import lk.ijse.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(productDTO);
    }

    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }

    public ProductDTO search(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.search(productId);
    }

    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(productDTO);
    }
    public boolean updateQTY(List<OrderProductDetailDTO> odList) throws SQLException, ClassNotFoundException {
        return productDAO.updateQTY(odList);
    }
    public List<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {
        return productDAO.getAllProducts();
    }

    public LocalDate getProductExpirationDate(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.getProductExpirationDate(productId);
    }

    public ProductDTO findById(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.findById(productId);
    }

    @Override
    public ProductDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<ProductDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    public List<ProductDTO> getExpiringProducts(LocalDate thresholdDate) throws SQLException, ClassNotFoundException {
        return productDAO.getExpiringProducts(thresholdDate);
    }
}