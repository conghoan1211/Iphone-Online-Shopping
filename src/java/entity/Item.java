/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.product.ColorProduct;
import entity.product.Product;

/**
 *
 * @author admin
 */
public class Item {
    private Product product;
    private int quantity;
    private ColorProduct color;
    
    public Item() {
    }

    public Item(Product product, int quantity, ColorProduct color) {
        this.product = product;
        this.quantity = quantity;
        this.color = color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ColorProduct getColor() {
        return color;
    }

    public void setColor(ColorProduct color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + ", color=" + color + '}';
    }    
    
    
}
