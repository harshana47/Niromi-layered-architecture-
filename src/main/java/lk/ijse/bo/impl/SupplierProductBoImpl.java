package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.SupplierProductDetailBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.SupplierProductDetailDAO;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dao.custom.impl.SupplierProductDAOImpl;
import lk.ijse.entity.SupplierProductDetail;
import lk.ijse.model.SupplierProductDetailDTO;

import java.sql.SQLException;

public class SupplierProductBoImpl implements SupplierProductDetailBO {

    SupplierProductDetailDAO supplierDAO = (SupplierProductDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERPRODUCTDETAIL);

    public boolean save(SupplierProductDetailDTO supplierProductDetailDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new SupplierProductDetail(supplierProductDetailDTO.getSupplierId(),supplierProductDetailDTO.getProductId()));
    }

    @Override
    public boolean update(SupplierProductDetailDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new SupplierProductDetail(dto.getSupplierId(),dto.getProductId()));
    }

    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(productId);
    }

    @Override
    public SupplierProductDetailDTO findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierProductDetailDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<SupplierProductDetailDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    @Override
    public SupplierProductDetailDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}