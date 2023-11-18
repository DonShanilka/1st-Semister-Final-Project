package lk.ijse.semisterfinal.model;

/*
    @author DanujaV
    @created 10/30/23 - 4:59 PM   
*/

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.CashierTm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public boolean saveOrderDetails(String orderId, List<CashierTm> cartTmList) throws SQLException {
        for(CashierTm tm : cartTmList) {
            if(!saveOrderDetails(orderId, (List<CashierTm>) tm)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetails(String orderId, CashierTm tm) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, orderId);
        pstm.setString(2, tm.getComItemId());
        pstm.setInt(3, Integer.parseInt(tm.getTxtQty()));
        pstm.setDouble(4, Double.parseDouble(tm.getItemPrice()));

        return pstm.executeUpdate() > 0;
    }
}
