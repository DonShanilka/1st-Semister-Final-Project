package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.Button;


public class CartTm {
    private String item_code;
    private String item_name;
    private double unit_price;
    private int qty;
    private double total;

    public CartTm(String code, String description, int qty, double unitPrice, double tot, Button btn) {
        item_code = code;
        item_name = description;
        this.qty = qty;
        this.unit_price = unitPrice;
        total = tot;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "item_code='" + item_code + '\'' +
                ", item_name='" + item_name + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
