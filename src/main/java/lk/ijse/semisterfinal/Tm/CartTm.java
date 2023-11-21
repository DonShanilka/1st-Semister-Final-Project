package lk.ijse.semisterfinal.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartTm {
    private String item_code;
    private String item_name;
    private double unit_price;
    private int qty;
    private double total;

    public CartTm(String code, String description, int qty, double unitPrice, double tot) {
        item_code = code;
        item_name = description;
        this.qty = qty;
        this.unit_price = unitPrice;
        total = tot;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
}
