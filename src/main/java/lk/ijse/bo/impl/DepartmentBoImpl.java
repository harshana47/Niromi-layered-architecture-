package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.DepartmentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DepartmentDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Department;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.DepartmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBoImpl implements DepartmentBO {

    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENT);

    @Override
    public boolean save(DepartmentDTO dto) throws SQLException, ClassNotFoundException {
        return departmentDAO.save(new Department(dto.getDepId(),dto.getName(),dto.getStaffCount()));
    }

    @Override
    public boolean delete(String depId) throws SQLException, ClassNotFoundException {
        return departmentDAO.delete(depId);
    }

    @Override
    public DepartmentDTO findById(String id) throws SQLException, ClassNotFoundException {
        try{
            Department dto = departmentDAO.findById(id);
            return new DepartmentDTO(dto.getDepId(),dto.getName(),dto.getStaffCount());
        }catch (SQLException e){
            throw new SQLException("Error in finding department by id: "+e.getMessage());
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepartmentDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public DepartmentDTO search(String depId) throws SQLException, ClassNotFoundException {
        try{
            Department dto = departmentDAO.search(depId);
            return new DepartmentDTO(dto.getDepId(),dto.getName(),dto.getStaffCount());
        }catch (SQLException e){
            throw new SQLException("Error in search department by id: "+e.getMessage());
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException {
        return departmentDAO.update(new Department(departmentDTO.getDepId(), departmentDTO.getName(),departmentDTO.getStaffCount()));
    }

    @Override
    public List<DepartmentDTO> load() throws SQLException, ClassNotFoundException {
        try {
            List<Department> department = departmentDAO.load();
            List<DepartmentDTO> departmentDTOS = new ArrayList<>();
            for (Department d : department) {
                departmentDTOS.add(new DepartmentDTO(d.getDepId(), d.getName(), d.getStaffCount()));
            }
            return departmentDTOS;
        }catch (SQLException e){
            throw new SQLException("Error while getting department: "+ e.getMessage());
        }catch (ClassCastException e){
            throw new ClassNotFoundException("Error while getting department: "+ e.getMessage());
        }
    }
}
