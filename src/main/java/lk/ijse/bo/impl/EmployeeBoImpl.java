package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
       return employeeDAO.save(employeeDTO);
    }

    @Override
    public boolean increaseStaffCount(String depId) throws SQLException, ClassNotFoundException {
        return employeeDAO.increaseStaffCount(depId);
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employeeDTO);
    }

    @Override
    public EmployeeDTO search(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(employeeId);
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public EmployeeDTO findById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public EmployeeDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<EmployeeDTO> DTOList) throws SQLException, ClassNotFoundException {
        DTOList.clear();
        employeeDAO.load(DTOList);
    }

    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllEmployees();
    }

    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public boolean decreaseStaffCount(String depId) throws SQLException, ClassNotFoundException {
        return employeeDAO.decreaseStaffCount(depId);
    }

    public List<String> getAllEmployeeEmails() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllEmployeeEmails();
    }
}
