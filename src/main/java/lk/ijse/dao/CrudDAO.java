package lk.ijse.dao;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.DepartmentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CrudDAO <T> extends SuperDAO{
    public boolean save(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public T findById(String id) throws SQLException, ClassNotFoundException;

    public T findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<T> EntityList) throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;

}
