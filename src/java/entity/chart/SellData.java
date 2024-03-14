/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.chart;

/**
 *
 * @author admin
 */
public class SellData {
    private int sell_year;
    private int sell_date;
    private int sell_quantity;

    public SellData() {
    }

    public SellData(int sell_year, int sell_date, int sell_quantity) {
        this.sell_year = sell_year;
        this.sell_date = sell_date;
        this.sell_quantity = sell_quantity;
    }

    public int getSell_year() {
        return sell_year;
    }

    public void setSell_year(int sell_year) {
        this.sell_year = sell_year;
    }

    public int getSell_date() {
        return sell_date;
    }

    public void setSell_date(int sell_date) {
        this.sell_date = sell_date;
    }

    public int getSell_quantity() {
        return sell_quantity;
    }

    public void setSell_quantity(int sell_quantity) {
        this.sell_quantity = sell_quantity;
    }

    @Override
    public String toString() {
        return "SellData{" + "sell_year=" + sell_year + ", sell_date=" + sell_date + ", sell_quantity=" + sell_quantity + '}';
    }
    
    
}
