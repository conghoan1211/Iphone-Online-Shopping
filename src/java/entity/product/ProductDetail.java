/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author admin
 */
public class ProductDetail {

    private int pd_id;
    private int pid;
    private String detail_img1;
    private String detail_img2;
    private String detail_img3;

    public ProductDetail() {
    }

    public ProductDetail(int pd_id, int pid, String detail_img1, String detail_img2, String detail_img3) {
        this.pd_id = pd_id;
        this.pid = pid;
        this.detail_img1 = detail_img1;
        this.detail_img2 = detail_img2;
        this.detail_img3 = detail_img3;
    }

    public int getPd_id() {
        return pd_id;
    }

    public void setPd_id(int pd_id) {
        this.pd_id = pd_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getDetail_img1() {
        return detail_img1;
    }

    public void setDetail_img1(String detail_img1) {
        this.detail_img1 = detail_img1;
    }

    public String getDetail_img2() {
        return detail_img2;
    }

    public void setDetail_img2(String detail_img2) {
        this.detail_img2 = detail_img2;
    }

    public String getDetail_img3() {
        return detail_img3;
    }

    public void setDetail_img3(String detail_img3) {
        this.detail_img3 = detail_img3;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "pd_id=" + pd_id + ", pid=" + pid + ", detail_img1=" + detail_img1 + ", detail_img2=" + detail_img2 + ", detail_img3=" + detail_img3 + '}';
    }

}
