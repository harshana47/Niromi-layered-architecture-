package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.DepartmentDAO;
import lk.ijse.entity.Department;
import lk.ijse.model.DepartmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public boolean save(Department entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO department (depId, name, staffCount) VALUES (?, ?, ?)",
                entity.getDepId(), entity.getName(), entity.getStaffCount());
    }

    @Override
    public boolean delete(String depId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM department WHERE depId = ?", depId);
    }

    @Override
    public Department findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM department WHERE depId = ?", id);
        if (resultSet.next()) {
            return new Department(
                    resultSet.getString("depId"),
                    resultSet.getString("name"),
                    resultSet.getInt("staffCount")
            );
        }
        return null;
    }

    @Override
    public Department findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Department search(String depId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM department WHERE depId = ?", depId);
        if (resultSet.next()) {
            return new Department(
                    resultSet.getString("depId"),
                    resultSet.getString("name"),
                    resultSet.getInt("staffCount")
            );
        }
        return null;
    }

    @Override
    public boolean update(Department entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE department SET name = ?, staffCount = ? WHERE depId = ?",
                entity.getName(), entity.getStaffCount(), entity.getDepId());
    }

    @Override
    public void load(ObservableList<Department> departmentList) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM department");
        while (resultSet.next()) {
            departmentList.add(new Department(
                    resultSet.getString("depId"),
                    resultSet.getString("name"),
                    resultSet.getInt("staffCount")
            ));
        }
    }
}
