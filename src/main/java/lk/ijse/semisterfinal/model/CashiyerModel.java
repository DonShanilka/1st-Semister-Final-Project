package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.CashiyerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.dto.PlaceOrderDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CashiyerModel {

  /*  public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        String sql = "SELECT item_code FROM item ORDER BY item_code DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return spiltOrderId(resultSet.getString(1));
        }
        return spiltOrderId(null);
    }

    private static String spiltOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "001";
        }
    }*/

    public boolean saveOrder(CashiyerDTO orderDto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO bill VALUES (?,?,?,?,?,?,?,?)");
        pstm.setString(1, orderDto.getBillId());
        pstm.setString(2, orderDto.getItemId());
        pstm.setString(3, orderDto.getItemName());
        pstm.setInt(4, orderDto.getQty());
        pstm.setDouble(5, orderDto.getItemPrice());
        pstm.setDouble(6, orderDto.getDiscount());
        pstm.setDouble(7, orderDto.getTotal());
        pstm.setDate(8, Date.valueOf(orderDto.getDate()));

        return pstm.executeUpdate() > 0;
    }

    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        System.out.println(placeOrderDto);

        String orderId = placeOrderDto.getOrderId();
        String customerId = placeOrderDto.getCustomerId();
        LocalDate date = placeOrderDto.getDate();

        Connection connection = null;
        try {
            connection = DbConnetion.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = OrderModel.saveOrder(orderId, customerId, date);
            if (isOrderSaved) {
                boolean isUpdated = ItemModel.updateItem(placeOrderDto.getCartTmList());
                if (isUpdated) {
                    boolean isOrderDetailSaved = OrderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getCartTmList());
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


    /*public static ArrayList<ItemDTO> getAllItemsInCart() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        String sql="SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            pstm.setString(1, resultSet.getString(1));
            pstm.setString(2, resultSet.getString(2));
            pstm.setString(3, resultSet.getString(3));
            pstm.setString(4, resultSet.getString(4));
            pstm.setString(5, resultSet.getString(5));
            pstm.setString(6, resultSet.getString(6));
        }

        return new ArrayList<>();
    }*/
}