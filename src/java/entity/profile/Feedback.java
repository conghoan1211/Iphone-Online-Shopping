/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.profile;

/**
 *
 * @author admin
 */
public class Feedback {
    private int pid, rate, display;
    private String username, cate, colorname;
    private String feedDate;
    private String describe, feeture, standard, content, image;

    public Feedback() {
    }

    public Feedback(int pid, int rate, int display, String username, String cate, String colorname, String feedDate, String describe, String feeture, String standard, String content, String image) {
        this.pid = pid;
        this.rate = rate;
        this.display = display;
        this.username = username;
        this.cate = cate;
        this.colorname = colorname;
        this.feedDate = feedDate;
        this.describe = describe;
        this.feeture = feeture;
        this.standard = standard;
        this.content = content;
        this.image = image;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public String getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFeeture() {
        return feeture;
    }

    public void setFeeture(String feeture) {
        this.feeture = feeture;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Feedback{" + "pid=" + pid + ", rate=" + rate + ", display=" + display + ", username=" + username + ", cate=" + cate + ", colorname=" + colorname + ", feedDate=" + feedDate + 
                ", describe=" + describe + ", feeture=" + feeture + ", standard=" + standard + ", content=" + content + ", image=" + image + '}';
    }

    
    
}
