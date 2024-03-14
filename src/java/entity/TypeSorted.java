/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class TypeSorted {
    private int sortID;
    private String sortName;

    public TypeSorted() {
    }

    public TypeSorted(int sortID, String sortName) {
        this.sortID = sortID;
        this.sortName = sortName;
    }

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "TypeSorted{" + "sortID=" + sortID + ", sortName=" + sortName + '}';
    }
    
    
}
