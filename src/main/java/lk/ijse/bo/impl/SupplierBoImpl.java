package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalesDAO;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.entity.Supplier;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getAddress(),supplierDTO.getContact(),supplierDTO.getEmail()));
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public SupplierDTO findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<SupplierDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    public boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getAddress(),supplierDTO.getContact(),supplierDTO.getEmail()));
    }

    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier s : suppliers) {
            SupplierDTO supplierDTO = new SupplierDTO(s.getSupplierId(), s.getName(), s.getAddress(), s.getContact(), s.getEmail());
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    public SupplierDTO search(String supplierId) throws SQLException, ClassNotFoundException {
        try {
            Supplier supplier = supplierDAO.search(supplierId);
            return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getAddress(),supplier.getContact(),supplier.getEmail());
        }catch (SQLException e) {
            throw new SQLException("Error searching : "+e.getMessage());
        }
    }

    public List<String> getAllSupplierNames() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllSupplierNames();
    }

    public String getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierIdByName(supplierName);
    }
}
