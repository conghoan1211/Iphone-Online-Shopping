/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class OrderStatus {
    private int orderid, pid, quantity, poldPrice, pcurrentPrice, pTotlePrice;
    private String pimage, ptitle, cname, pstatus, pcolor;
    private String orderDate;
    private int delivered, accepted, feedback;

    public OrderStatus() {
    }

    public OrderStatus(int orderid, int pid, int quantity, int poldPrice, int pcurrentPrice, int pTotlePrice, String pimage, String ptitle, 
            String cname, String pstatus, String pcolor, String orderDate, int delivered, int accepted, int feedback) {
        this.orderid = orderid;
        this.pid = pid;
        this.quantity = quantity;                                                                                          
        this.poldPrice = poldPrice;
        this.pcurrentPrice = pcurrentPrice;
        this.pTotlePrice = pTotlePrice;
        this.pimage = pimage;
        this.ptitle = ptitle;
        this.cname = cname;
        this.pstatus = pstatus;
        this.pcolor = pcolor;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.accepted = accepted;
        this.feedback = feedback;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPoldPrice() {
        return poldPrice;
    }

    public void setPoldPrice(int poldPrice) {
        this.poldPrice = poldPrice;
    }

    public int getPcurrentPrice() {
        return pcurrentPrice;
    }

    public void setPcurrentPrice(int pcurrentPrice) {
        this.pcurrentPrice = pcurrentPrice;
    }

    public int getpTotlePrice() {
        return pTotlePrice;
    }

    public void setpTotlePrice(int pTotlePrice) {
        this.pTotlePrice = pTotlePrice;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getPcolor() {
        return pcolor;
    }

    public void setPcolor(String pcolor) {
        this.pcolor = pcolor;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "OrderStatus{" + "orderid=" + orderid + ", pid=" + pid + ", quantity=" + quantity + ", poldPrice=" + poldPrice + ", pcurrentPrice=" + pcurrentPrice + ", pTotlePrice=" + pTotlePrice + ", pimage=" + pimage + ", ptitle=" + ptitle + ", cname=" + cname + ", pstatus=" + pstatus + ", pcolor=" + pcolor + ", orderDate=" + orderDate + ", delivered=" + delivered + ", accepted=" + accepted + ", feedback=" + feedback + '}';
    }

    
    
}

















