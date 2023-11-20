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
    private String unit_price;
    private double total;
}
