package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OrderDetailBO;
import lk.ijse.bo.custom.SalesBO;
import lk.ijse.bo.impl.OrderDetailBoImpl;
import lk.ijse.bo.impl.SalesBoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderProductDetailDTO;
import lk.ijse.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.dao.custom.impl.SalesDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesFormController {
    public TableColumn<OrderProductDetailDTO, String> colOrderId;
    public TableColumn<OrderProductDetailDTO, String> colProductId;
    public TableColumn<OrderProductDetailDTO, Integer> colQuantity;
    public TableColumn<OrderProductDetailDTO, Double> colPrice;
    public TableColumn<OrderProductDetailDTO, LocalDate> colDate;
    public Button btnBack;
    public Button btnSearch;
    public TableView<OrderProductDetailDTO> tblSales;
    public Button btnReport;
    public TextField txtDate;
    public TextField txtId;
    public Button btnRefresh;

    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERDETAIL);
    SalesBO salesBO = (SalesBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALES);

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        try {
            loadOrderProductDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadOrderProductDetails() throws SQLException, ClassNotFoundException {
        List<OrderProductDetailDTO> orderProductDetailDTOS = orderDetailBO.getAllOrderProductDetails();
        ObservableList<OrderProductDetailDTO> data = FXCollections.observableArrayList(orderProductDetailDTOS);
        tblSales.setItems(data);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtId.getText();

        OrderProductDetailDTO sales = salesBO.search(orderId);
        if (sales != null) {
            tblSales.getItems().clear();
            tblSales.getItems().add(sales);
        } else {
            new Alert(Alert.AlertType.ERROR,"Cant find sales id").show();
        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        new Thread(()->{
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/Sales.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_txtDate", txtDate.getText());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DbConnection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
        }).start();
    }

    public void btnRefreashOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        loadOrderProductDetails();
    }
}
