package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Promotion;
import lk.ijse.model.PromotionDTO;

import java.sql.SQLException;
import java.util.List;

public interface PromotionDAO extends CrudDAO<Promotion> {
    public List<String> getAllPromoNames() throws SQLException, ClassNotFoundException;

    public List<Promotion> getAllPromotions() throws SQLException, ClassNotFoundException;

    public String findPromotionByName(String promoName) throws SQLException, ClassNotFoundException;
}
