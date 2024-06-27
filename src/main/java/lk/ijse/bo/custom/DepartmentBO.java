package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.DepartmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DepartmentBO extends SuperBO {
    public boolean save(DepartmentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String depId) throws SQLException, ClassNotFoundException;

    public DepartmentDTO findById(String id) throws SQLException, ClassNotFoundException;

    public DepartmentDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public DepartmentDTO search(String depId) throws SQLException, ClassNotFoundException;

    public boolean update(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<DepartmentDTO> departmentDTOList) throws SQLException, ClassNotFoundException;
}
