package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.entity.Employee;
import lk.ijse.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (employeeId, name, depId, position, duty, email) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getEmployeeId(), entity.getName(), entity.getDepId(), entity.getPosition(),
                entity.getDuty(), entity.getEmail());
    }

    @Override
    public boolean increaseStaffCount(String depId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE department SET staffCount = staffCount + 1 WHERE depId = ?", depId);
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name = ?, depId = ?, position = ?, duty = ?, email = ? WHERE employeeId = ?",
                entity.getName(), entity.getDepId(), entity.getPosition(), entity.getDuty(),
                entity.getEmail(), entity.getEmployeeId());
    }

    @Override
    public Employee search(String employeeId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE employeeId = ?", employeeId);
        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString("employeeId"),
                    resultSet.getString("name"),
                    resultSet.getString("depId"),
                    resultSet.getString("position"),
                    resultSet.getString("duty"),
                    resultSet.getString("email")
            );
        }
        return null;
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employeeId = ?", employeeId);
    }

    @Override
    public Employee findById(String id) throws SQLException, ClassNotFoundException {
        // Implement as required
        return null;
    }

    @Override
    public Employee findByPhone(String phone) throws SQLException, ClassNotFoundException {
        // Implement as required
        return null;
    }

    @Override
    public List<Employee> load() throws SQLException, ClassNotFoundException {
        return null;
    }

    public void load(ObservableList<Employee> EntityList) throws SQLException, ClassNotFoundException {
        EntityList.clear();
        EntityList.addAll(getAllEmployees());
    }

    public List getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        while (resultSet.next()) {
            Employee employee = new Employee(
                    resultSet.getString("employeeId"),
                    resultSet.getString("name"),
                    resultSet.getString("depId"),
                    resultSet.getString("position"),
                    resultSet.getString("duty"),
                    resultSet.getString("email")
            );
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM employee");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean decreaseStaffCount(String depId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE department SET staffCount = staffCount - 1 WHERE depId = ?", depId);
    }

    public List<String> getAllEmployeeEmails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT email FROM employee");
        List<String> emailList = new ArrayList<>();
        while (resultSet.next()) {
            emailList.add(resultSet.getString("email"));
        }
        return emailList;
    }
}
