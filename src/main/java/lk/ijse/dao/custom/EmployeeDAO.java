package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Employee;
import lk.ijse.model.EmployeeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

    public boolean decreaseStaffCount(String depId) throws SQLException, ClassNotFoundException;

    public boolean increaseStaffCount(String depId) throws SQLException, ClassNotFoundException;

    public int getEmployeeCount() throws SQLException, ClassNotFoundException;

    public List<String> getAllEmployeeEmails() throws SQLException, ClassNotFoundException;

    public List<Employee> getAllEmployees() throws SQLException, ClassNotFoundException;
}
