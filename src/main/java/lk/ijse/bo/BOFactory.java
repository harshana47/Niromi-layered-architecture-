package lk.ijse.bo;

import lk.ijse.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,DEPARTMENT,EMPLOYEE,ORDER,ORDERDETAIL,PAYMENT,PRODUCT,PROMOTION,SALES,SUPPLIER,SUPPLIERPRODUCTDETAIL,USER
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBoImpl();
            case DEPARTMENT:
                return new DepartmentBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case ORDER:
                return new OrderBoImpl();
            case ORDERDETAIL:
                return new OrderDetailBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case PRODUCT:
                return new ProductBoImpl();
            case PROMOTION:
                return new PromotionBoImpl();
            case SALES:
                return new SalesBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case SUPPLIERPRODUCTDETAIL:
                return new SupplierProductBoImpl();
            case USER:
                return new UserBoImpl();
            default:
                return null;
        }
    }
}
