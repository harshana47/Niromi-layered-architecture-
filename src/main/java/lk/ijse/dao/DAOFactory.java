package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,DEPARTMENT,EMPLOYEE,ORDER,ORDERDETAIL,PAYMENT,PRODUCT,PROMOTION,SALES,SUPPLIER,SUPPLIERPRODUCTDETAIL,USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DEPARTMENT:
                return new DepartmentDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PROMOTION:
                return new PromotionDAOImpl();
            case SALES:
                return new SalesDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIERPRODUCTDETAIL:
                return new SupplierProductDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }

}
