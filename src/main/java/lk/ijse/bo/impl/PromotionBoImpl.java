package lk.ijse.bo.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.PromotionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PromotionDAO;
import lk.ijse.dao.custom.impl.PromotionDAOImpl;
import lk.ijse.entity.Promotion;
import lk.ijse.model.PromotionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionBoImpl implements PromotionBO {

    PromotionDAO promotionDAO = (PromotionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROMOTION);

    public boolean save(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        return promotionDAO.save(new Promotion(promotionDTO.getPromoId(), promotionDTO.getPromoName(), promotionDTO.getDiscountPercentage()));
    }

    public boolean update(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        return promotionDAO.update(new Promotion(promotionDTO.getPromoId(), promotionDTO.getPromoName(), promotionDTO.getDiscountPercentage()));
    }

    public boolean delete(String promoId) throws SQLException, ClassNotFoundException {
        return promotionDAO.delete(promoId);
    }

    public PromotionDTO search(String promoId) throws SQLException, ClassNotFoundException {
        try {
            Promotion promotion = promotionDAO.search(promoId);
            return new PromotionDTO(promotion.getPromoId(), promotion.getPromoName(), promotion.getDiscountPercentage());
        }catch (Exception e) {
            throw new SQLException("Promotion not found!");
        }
    }

    public List<PromotionDTO> getAllPromotions() throws SQLException, ClassNotFoundException {
        List<Promotion> promotions = new ArrayList<>();
        ArrayList<PromotionDTO> promotionDTOS = new ArrayList<>();
        for (Promotion p:promotions){
            PromotionDTO promotionDTO = new PromotionDTO(p.getPromoId(),p.getPromoName(),p.getDiscountPercentage());
            promotionDTOS.add(promotionDTO);
        }
        return promotionDTOS;
    }

    public List<String> getAllPromoNames() throws SQLException, ClassNotFoundException {
        return promotionDAO.getAllPromoNames();
    }

    public PromotionDTO findById(String promoId) throws SQLException, ClassNotFoundException {
        try {
            Promotion promotion = promotionDAO.findById(promoId);
            return new PromotionDTO(promotion.getPromoId(), promotion.getPromoName(), promotion.getDiscountPercentage());
        }catch (SQLException e) {
            throw new SQLException("Promotion not found!");
        }
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