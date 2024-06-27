package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Supplier;
import lk.ijse.model.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {

    public List<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public List<String> getAllSupplierNames() throws SQLException, ClassNotFoundException;

    public String getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException;
}
