package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.CashiyerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashiyerModel {

    public static List<ItemDTO> getAllCustomer() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ItemDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new ItemDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    ));
        }
        return dtoList;
    }
}
