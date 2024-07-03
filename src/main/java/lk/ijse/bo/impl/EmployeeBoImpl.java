package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;
import lk.ijse.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getDepId(),employeeDTO.getPosition(),employeeDTO.getDuty(),employeeDTO.getEmail()));
    }

    @Override
    public boolean increaseStaffCount(String depId) throws SQLException, ClassNotFoundException {
        return employeeDAO.increaseStaffCount(depId);
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getDepId(),employeeDTO.getPosition(),employeeDTO.getDuty(),employeeDTO.getEmail()));
    }

    @Override
    public EmployeeDTO search(String employeeId) throws SQLException, ClassNotFoundException {
        try {
            Employee employeeDTO = employeeDAO.search(employeeId);
            return new EmployeeDTO(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getDepId(),employeeDTO.getPosition(),employeeDTO.getDuty(),employeeDTO.getEmail());
        } catch (SQLException e) {
            throw new SQLException("Error in searching employee by id: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error in searching employee by id: " + e.getMessage());
        }
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
    public List<EmployeeDTO> load() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.load();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e:employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(e.getEmployeeId(),e.getName(),e.getDepId(),e.getPosition(),e.getDuty(),e.getEmail());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            List<EmployeeDTO> employeeDTOs = new ArrayList<>();
            for (Employee employeeDTO : employees) {
                employeeDTOs.add(new EmployeeDTO(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getDepId(),employeeDTO.getPosition(),employeeDTO.getDuty(),employeeDTO.getEmail()));
            }
            return employeeDTOs;
        } catch (SQLException e) {
            throw new SQLException("Error in getting all employees: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error in getting all employees: " + e.getMessage());
        }
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        try {
            return employeeDAO.getEmployeeCount();
        } catch (SQLException e) {
            throw new SQLException("Error in getting employee count: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error in getting employee count: " + e.getMessage());
        }
    }

    @Override
    public boolean decreaseStaffCount(String depId) throws SQLException, ClassNotFoundException {
        return employeeDAO.decreaseStaffCount(depId);
    }

    @Override
    public List<String> getAllEmployeeEmails() throws SQLException, ClassNotFoundException {
        try {
            return employeeDAO.getAllEmployeeEmails();
        } catch (SQLException e) {
            throw new SQLException("Error in getting all employee emails: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error in getting all employee emails: " + e.getMessage());
        }
    }
}
