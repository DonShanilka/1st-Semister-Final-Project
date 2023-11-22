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
    private int qty;
    private double unit_price;
    private double total;
    private Button button;




}
