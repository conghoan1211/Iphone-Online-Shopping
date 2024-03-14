/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author admin
 */
public class Product_Variant {

    private int provId;
    private int pid;
    private int color_id;
    private int model_id;

    public Product_Variant() {
    }

    public Product_Variant(int provId, int pid, int color_id, int model_id) {
        this.provId = provId;
        this.pid = pid;
        this.color_id = color_id;
        this.model_id = model_id;
    }

    public int getProvId() {
        return provId;
    }

    public void setProvId(int provId) {
        this.provId = provId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    @Override
    public String toString() {
        return "ProductVarient{" + "provId=" + provId + ", pid=" + pid + ", size_id=" + model_id + ", color_id=" + color_id + '}';
    }

}
