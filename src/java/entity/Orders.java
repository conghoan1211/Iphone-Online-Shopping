/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Orders {

    private int orderId;
    private String username;
    private Date orderDate;
    private int delivered, feedback, accepted;
    private int discount;
    private int totalPrice;

    public Orders() {
    }

    public Orders(int orderId, String username, Date orderDate, int delivered, int discount, int totalPrice, int accepted, int feedback) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.accepted = accepted;
        this.feedback = feedback;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", username=" + username + ", orderDate=" + orderDate + ", delivered=" + delivered + ", feedback=" + feedback + ", accepted=" + accepted + ", discount=" + discount + ", totalPrice=" + totalPrice + '}';
    }

}
