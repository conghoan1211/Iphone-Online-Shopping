/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.profile;

/**
 *
 * @author admin
 */
public class Account {
    private int accID;
    private String username;
    private String password;
    private int isAdmin;
    private int isBlock;

    public Account() {
    }

    public Account(int accID, String username, String password, int isAdmin, int isBlock) {
        this.accID = accID;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBlock = isBlock;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    @Override
    public String toString() {
        return "Account{" + "accID=" + accID + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + ", isBlock=" + isBlock + '}';
    }

    
            
}
