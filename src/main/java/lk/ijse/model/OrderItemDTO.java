package lk.ijse.model;

import java.io.Serializable;

public class OrderItemDTO implements Serializable {
    private String productId;
    private int quantity;
    private double itemPrice;

    public OrderItemDTO(String productId, int quantity, double itemPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
