package lk.ijse.semisterfinal.dto;

/*
    @author DanujaV
    @created 10/30/23 - 4:32 PM   
*/

import lk.ijse.semisterfinal.Tm.CashierTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDto {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private List<CashierTm> cartTmList = new ArrayList<>();

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(String orderId, LocalDate date, String customerId, List<CashierTm> cartTmList) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.cartTmList = cartTmList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<CashierTm> getCartTmList() {
        return cartTmList;
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
