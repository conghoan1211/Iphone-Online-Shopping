/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.chart;

/**
 *
 * @author admin
 */
public class StockData {
    private String cateName;
    private int stock;

    public StockData() {
    }

    public StockData(String cateName, int stock) {
        this.cateName = cateName;
        this.stock = stock;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockData{" + "cateName=" + cateName + ", stock=" + stock + '}';
    }

   
    
}
