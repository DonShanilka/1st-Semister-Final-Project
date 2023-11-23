package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;

import java.sql.*;
import java.time.LocalDate;

public class OrderModel {
    public String getLastId() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return (resultSet.getString(1));
        }
        return null;
    }

//    private String splitOrderId(String currentOrderId) {
//        if(currentOrderId != null) {
//            String[] split = currentOrderId.split("O0");
//
//            int id = Integer.parseInt(split[1]); //01
//            id++;
//            return "O00" + id;
//        } else {
//            return "O001";
//        }
//    }

    public static boolean saveOrder(String orderId, String customerId, LocalDate date) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, customerId);
        pstm.setString(3, String.valueOf(date));

        return pstm.executeUpdate() > 0;
    }
}
