package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Employee;
import lk.ijse.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public boolean increaseStaffCount(String depId) throws SQLException, ClassNotFoundException;

    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public EmployeeDTO search(String employeeId) throws SQLException, ClassNotFoundException;

    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException;

    public EmployeeDTO findById(String id) throws SQLException, ClassNotFoundException;

    public EmployeeDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> load() throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    public int getEmployeeCount() throws SQLException, ClassNotFoundException;

    public boolean decreaseStaffCount(String depId) throws SQLException, ClassNotFoundException;

    public List<String> getAllEmployeeEmails() throws SQLException, ClassNotFoundException;
}
