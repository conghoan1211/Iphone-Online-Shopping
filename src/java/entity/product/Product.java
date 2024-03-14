/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

import entity.product.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Product {

    private int id;
    private String image;
    private String title;
    private int oldPrice;
    private int currentPrice;
    private int amountOfSold;
    private String origin;
    private String status;
    private Category cate;
    private String describe;
    private int quantity_in_stock;
    List<ColorProduct> listColor = new ArrayList<>();

    public Product() {
    }

    public Product(int id, String image, String title, int oldPrice, int currentPrice, int amountOfSold,
            String origin, String status, Category cate, String describe, int quantity_in_stock) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.oldPrice = oldPrice;
        this.currentPrice = currentPrice;
        this.amountOfSold = amountOfSold;
        this.origin = origin;
        this.status = status;
        this.cate = cate;
        this.describe = describe;
        this.quantity_in_stock = quantity_in_stock;
    }

    public List<ColorProduct> getListColor() {
        return listColor;
    }

    public void setListColor(List<ColorProduct> listColor) {
        this.listColor = listColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getAmountOfSold() {
        return amountOfSold;
    }

    public void setAmountOfSold(int amountOfSold) {
        this.amountOfSold = amountOfSold;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", image=" + image + ", title=" + title + ", oldPrice=" + oldPrice + ", currentPrice=" + currentPrice 
                + ", amountOfSold=" + amountOfSold + ", origin=" + origin + ", status=" + status + ", cate=" + cate + ", describe=" + describe 
                + ", quantity_in_stock=" + quantity_in_stock + ", listColor=" + listColor + '}';
    }


}
