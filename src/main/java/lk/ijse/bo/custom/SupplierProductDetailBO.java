package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.SupplierProductDetailDTO;

import java.sql.SQLException;

public interface SupplierProductDetailBO extends SuperBO {

    public boolean save(SupplierProductDetailDTO supplierProductDetailDTO) throws SQLException, ClassNotFoundException;

    public boolean update(SupplierProductDetailDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String productId) throws SQLException, ClassNotFoundException;

    public SupplierProductDetailDTO findById(String id) throws SQLException, ClassNotFoundException;

    public SupplierProductDetailDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<SupplierProductDetailDTO> DTOList) throws SQLException, ClassNotFoundException;

    public SupplierProductDetailDTO search(String id) throws SQLException, ClassNotFoundException;
}
