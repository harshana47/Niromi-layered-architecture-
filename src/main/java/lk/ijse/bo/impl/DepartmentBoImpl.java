package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.DepartmentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.DepartmentDAO;
import lk.ijse.dao.custom.impl.DepartmentDAOImpl;
import lk.ijse.model.DepartmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentBoImpl implements DepartmentBO {

    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENT);

    @Override
    public boolean save(DepartmentDTO dto) throws SQLException, ClassNotFoundException {
        return departmentDAO.save(dto);
    }

    @Override
    public boolean delete(String depId) throws SQLException, ClassNotFoundException {
        return departmentDAO.delete(depId);
    }

    @Override
    public DepartmentDTO findById(String id) throws SQLException, ClassNotFoundException {
        return departmentDAO.findById(id);
    }

    @Override
    public DepartmentDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public DepartmentDTO search(String depId) throws SQLException, ClassNotFoundException {
        return departmentDAO.search(depId);
    }

    @Override
    public boolean update(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException {
        return departmentDAO.update(departmentDTO);
    }

    @Override
    public void load(ObservableList<DepartmentDTO> departmentDTOList) throws SQLException, ClassNotFoundException {
        departmentDAO.load(departmentDTOList);
    }
}
