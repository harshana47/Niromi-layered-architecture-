package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException;

    public SupplierDTO findById(String id) throws SQLException, ClassNotFoundException;

    public SupplierDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<SupplierDTO> DTOList) throws SQLException, ClassNotFoundException;

    public boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public SupplierDTO search(String supplierId) throws SQLException, ClassNotFoundException;

    public List<String> getAllSupplierNames() throws SQLException, ClassNotFoundException;

    public  String getSupplierIdByName(String supplierName) throws SQLException, ClassNotFoundException;
}
