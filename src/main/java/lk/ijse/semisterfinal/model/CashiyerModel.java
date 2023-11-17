package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.CashiyerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class CashiyerModel {
    private ItemModel itemModel = new ItemModel();
    private BillModel billModel = new BillModel();

    public boolean placeOrder(CashiyerDTO cashiyerDTO) throws SQLException {
        System.out.println(cashiyerDTO);

        String orderId = cashiyerDTO.getOrderId();
        String customerId = cashiyerDTO.getCustomerId();
        LocalDate date = cashiyerDTO.getDate();

        Connection connection = null;
        try {
            connection = DbConnetion.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = billModel.saveOrder(orderId, customerId, date);
            if (isOrderSaved) {
                boolean isUpdated = itemModel.updateItem(placeOrderDto.getCartTmList());
                if (isUpdated) {
                    boolean isOrderDetailSaved = orderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getCartTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
