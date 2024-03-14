/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.profile;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class AccDetail {
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String gender;
    private Date dob;

    public AccDetail() {
    }

    public AccDetail(String username, String nickname, String email, String phone, String gender, Date dob) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "AccDetail{" + "username=" + username + ", nickname=" + nickname + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", dob=" + dob + '}';
    }

    
            
}
