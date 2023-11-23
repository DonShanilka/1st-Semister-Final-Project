package lk.ijse.semisterfinal.Tm;

import javafx.scene.control.Button;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartTm {
    private String item_code;
    private String item_name;
    private double unit_price;
    private int qty;
    private double total;
    private Button button;

    private String ItemPrice;
    private String ItemName;
    private String billDate;
    private String txtQty;
    private String btnAddToBill;
    private String comItemId;

    public CartTm(String code, String description, double unitPrice, int qty, double tot, Button btn) {
        this.item_code = code;
        this.item_name = description;
        this.unit_price = unitPrice;
        this.qty = qty;
        this.total = tot;
        this.button = btn;
    }
}
