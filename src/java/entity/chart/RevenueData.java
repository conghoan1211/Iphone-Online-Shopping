/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.chart;

/**
 *
 * @author admin
 */
public class RevenueData {

    private int orderMonth;
    private int orderYear;
    private int categoryId;
    private int totalPrice;

    public RevenueData() {
    }

    public RevenueData(int orderMonth, int orderYear, int categoryId, int totalPrice) {
        this.orderMonth = orderMonth;
        this.orderYear = orderYear;
        this.categoryId = categoryId;
        this.totalPrice = totalPrice;
    }

    public int getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(int orderMonth) {
        this.orderMonth = orderMonth;
    }

    public int getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(int orderYear) {
        this.orderYear = orderYear;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    
}
