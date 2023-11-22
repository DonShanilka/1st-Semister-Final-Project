package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.CashiyerDTO;
import java.sql.*;

public class CashiyerModel {

    public static String generateNextOrderId() throws SQLException {
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
        if (currentOrderId != null ){
            String[] spilt = currentOrderId.split("O0");
            int id = Integer.parseInt(spilt[1]);
            id++;
            return "O00"+id;
        }else {
            return "O001";
        }
    }

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
}