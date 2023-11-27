package lk.ijse.semisterfinal.dto;

import lk.ijse.semisterfinal.Tm.CartTm;
import lombok.Getter;
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
    private String itemCode;
    private int qty;
    private double unitPrice;
    private List<CartTm> cartTmList = new ArrayList<>();

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(String orderId, LocalDate date, String customerId, List<CartTm> cartTmList) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.cartTmList = cartTmList;
    }

    public PlaceOrderDto(String orderId, String customerId, String date) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = LocalDate.parse(date);
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

    public void setCartTmList(List<CartTm> cartTmList) {
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
