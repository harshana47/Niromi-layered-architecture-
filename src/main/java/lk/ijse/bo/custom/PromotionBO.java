package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.model.PromotionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PromotionBO extends SuperBO {
    public boolean save(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException;

    public boolean update(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String promoId) throws SQLException, ClassNotFoundException;

    public PromotionDTO search(String promoId) throws SQLException, ClassNotFoundException;

    public List<PromotionDTO> getAllPromotions() throws SQLException, ClassNotFoundException;

    public List<String> getAllPromoNames() throws SQLException, ClassNotFoundException;

    public PromotionDTO findById(String promoId) throws SQLException, ClassNotFoundException;

    public PromotionDTO findByPhone(String phone) throws SQLException, ClassNotFoundException;

    public void load(ObservableList<PromotionDTO> DTOList) throws SQLException, ClassNotFoundException;

    public String findPromotionByName(String promoName) throws SQLException, ClassNotFoundException;
}
