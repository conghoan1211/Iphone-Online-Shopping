/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class OrderDetail {
    private int detailId;
    private int orderId;
    private int productID;
    private int quantity, color_id;
    private int price;
    private int totalPrice;

    public OrderDetail() {
    }

    public OrderDetail(int detailId, int orderId, int productID, int quantity, int color_id, int price, int totalPrice) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.productID = productID;
        this.quantity = quantity;
        this.color_id = color_id;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "detailId=" + detailId + ", orderId=" + orderId + ", productID=" + productID + ", quantity=" + quantity + ", color_id=" + color_id + ", price=" + price + ", totalPrice=" + totalPrice + '}';
    }

    
}
