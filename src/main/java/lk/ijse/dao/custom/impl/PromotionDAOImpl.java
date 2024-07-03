package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PromotionDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Promotion;
import lk.ijse.model.PromotionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAOImpl implements PromotionDAO {

    public boolean save(Promotion entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO promotion (promoId, promoName, discountPercentage) VALUES (?, ?, ?)",entity.getPromoId(),entity.getPromoName(),entity.getDiscountPercentage());
    }

    public boolean update(Promotion entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE promotion SET promoName=?, discountPercentage=? WHERE promoId=?",entity.getPromoName(),entity.getDiscountPercentage(),entity.getPromoId());
    }

    public boolean delete(String promoId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM promotion WHERE promoId=?",promoId);
    }

    public Promotion search(String promoId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM promotion WHERE promoId=?",promoId);
        if (resultSet.next()) {
            return new Promotion(
                    resultSet.getString("promoId"),
                    resultSet.getString("promoName"),
                    resultSet.getString("discountPercentage")
            );
        }
        return null;
    }

    public List<Promotion> getAllPromotions() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM promotion");
        List<Promotion> promotions = new ArrayList<>();
        while (resultSet.next()) {
            promotions.add(new Promotion(
                    resultSet.getString("promoId"),
                    resultSet.getString("promoName"),
                    resultSet.getString("discountPercentage")
            ));
        }
        return promotions;
    }

    public List<String> getAllPromoNames() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT promoName FROM promotion");
        ArrayList<String> allPromos = new ArrayList();
        while (rst.next()) {
            Promotion promotions = new Promotion(
                    //rst.getString("promoId"),
                    rst.getString("promoName")
                    //rst.getString("discountPercentage")
            );
            allPromos.add(promotions.getPromoName());
        }
        return allPromos;
    }

    public Promotion findById(String promoId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM promotion WHERE promoId=?",promoId);
        if (resultSet.next()){
            return new Promotion(
                    resultSet.getString("promoId"),
                    resultSet.getString("promoName"),
                    resultSet.getString("discountPercentage")
            );
        }
        return null;
    }

    @Override
    public Promotion findByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List load() throws SQLException, ClassNotFoundException {
        return null;
    }

    public String findPromotionByName(String promoName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT promoId FROM promotion WHERE promoName = ?",promoName);
        if (resultSet.next()){
            return resultSet.getString("promoId");
        }
        return null;
    }
}