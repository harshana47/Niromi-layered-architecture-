package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.ProductBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProductDAO;
import lk.ijse.dao.custom.impl.ProductDAOImpl;
import lk.ijse.entity.Customer;
import lk.ijse.entity.OrderProductDetail;
import lk.ijse.entity.Product;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.OrderProductDetailDTO;
import lk.ijse.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    @Override
    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(productDTO.getProductId(), productDTO.getName(),productDTO.getExpireDate(), productDTO.getPrice(), productDTO.getQtyOnHand(), productDTO.getEmployeeId(), productDTO.getPromoId(), productDTO.getSupplierName()));
    }

    @Override
    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }

    public ProductDTO search(String productId) throws SQLException, ClassNotFoundException {
        try {
            Product product = productDAO.search(productId);
            return new ProductDTO(product.getProductId(), product.getName(), product.getExpireDate(), product.getPrice(), product.getQtyOnHand(), product.getEmployeeId(), product.getPromoId(), product.getSupplierName());
        }catch (SQLException e){
            throw new SQLException("Product not found: " + e.getMessage());
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Class not found: " + e.getMessage());
        }
    }

    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(productDTO.getProductId(), productDTO.getName(),productDTO.getExpireDate(), productDTO.getPrice(), productDTO.getQtyOnHand(), productDTO.getEmployeeId(), productDTO.getPromoId(), productDTO.getSupplierName()));
    }
    @Override
    public boolean updateQTY(List<OrderProductDetail> odList) throws SQLException, ClassNotFoundException {
        return productDAO.updateQTY(odList);
    }
    public List<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = productDAO.getAllProducts();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p:products){
            ProductDTO productDTO = new ProductDTO(p.getProductId(), p.getName(),p.getExpireDate(), p.getPrice(), p.getQtyOnHand(), p.getEmployeeId(), p.getPromoId(), p.getSupplierName());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public LocalDate getProductExpirationDate(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.getProductExpirationDate(productId);
    }

    public ProductDTO findById(String productId) throws SQLException, ClassNotFoundException {
        try {
            Product product = productDAO.findById(productId);
            return new ProductDTO(product.getProductId(), product.getName(), product.getExpireDate(), product.getPrice(), product.getQtyOnHand(), product.getEmployeeId(), product.getPromoId(), product.getSupplierName());
        }catch (SQLException e) {
            throw new SQLException("Product not found: " + e.getMessage());
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Class not found: " + e.getMessage());
        }
    }

    @Override
    public ProductDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<ProductDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    public List<ProductDTO> getExpiringProducts(LocalDate thresholdDate) throws SQLException, ClassNotFoundException {
        List<Product> products = productDAO.getExpiringProducts(thresholdDate);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p: products){
            ProductDTO productDTO = new ProductDTO(p.getProductId(), p.getName(), p.getExpireDate(), p.getPrice(), p.getQtyOnHand(), p.getEmployeeId(), p.getPromoId(), p.getSupplierName());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}