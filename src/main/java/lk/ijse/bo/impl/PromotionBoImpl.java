package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.PromotionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PromotionDAO;
import lk.ijse.dao.custom.impl.PromotionDAOImpl;
import lk.ijse.model.PromotionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionBoImpl implements PromotionBO {

    PromotionDAO promotionDAO = (PromotionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROMOTION);

    public boolean save(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        return promotionDAO.save(promotionDTO);
    }

    public boolean update(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        return promotionDAO.update(promotionDTO);
    }

    public boolean delete(String promoId) throws SQLException, ClassNotFoundException {
        return promotionDAO.delete(promoId);
    }

    public PromotionDTO search(String promoId) throws SQLException, ClassNotFoundException {
        return promotionDAO.search(promoId);
    }

    public List<PromotionDTO> getAllPromotions() throws SQLException, ClassNotFoundException {
        return promotionDAO.getAllPromotions();
    }

    public List<String> getAllPromoNames() throws SQLException, ClassNotFoundException {
        return promotionDAO.getAllPromoNames();
    }

    public PromotionDTO findById(String promoId) throws SQLException, ClassNotFoundException {
        return promotionDAO.findById(promoId);
    }

    @Override
    public PromotionDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void load(ObservableList<PromotionDTO> DTOList) throws SQLException, ClassNotFoundException {

    }

    public String findPromotionByName(String promoName) throws SQLException, ClassNotFoundException {
        return promotionDAO.findPromotionByName(promoName);
    }
}