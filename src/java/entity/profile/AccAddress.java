/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.profile;

/**
 *
 * @author admin
 */
public class AccAddress {
    private String usernameAdd;
    private String nickname;
    private String phone_addr;
    private String address;
    private String address_detail;
    private String status;

    public AccAddress() {
    }

    public AccAddress(String usernameAdd, String nickname, String phone_addr, String address, String address_detail, String status) {
        this.usernameAdd = usernameAdd;
        this.nickname = nickname;
        this.phone_addr = phone_addr;
        this.address = address;
        this.address_detail = address_detail;
        this.status = status;
    }

    public String getUsernameAdd() {
        return usernameAdd;
    }

    public void setUsernameAdd(String usernameAdd) {
        this.usernameAdd = usernameAdd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone_addr() {
        return phone_addr;
    }

    public void setPhone_addr(String phone_addr) {
        this.phone_addr = phone_addr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Address{" + "usernameAdd=" + usernameAdd + ", nickname=" + nickname + ", phone_addr=" + phone_addr + ", address=" + address + ", address_detail=" + address_detail + ", status=" + status + '}';
    }
   
    
}
