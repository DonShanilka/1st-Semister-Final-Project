package lk.ijse.semisterfinal.dto;

import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.Tm.CashierTm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlaceOrderDto {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private List<CashierTm> cartTmList = new ArrayList<>();

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(String orderId, LocalDate date, String customerId, List<CartTm> cartTmList) {
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCartTmList(List<CashierTm> cartTmList) {
        this.cartTmList = cartTmList;
    }

    @Override
    public String toString() {
        return "PlaceOrderDto{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", cartTmList=" + cartTmList +
                '}';
    }
}
