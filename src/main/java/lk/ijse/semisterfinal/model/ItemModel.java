package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemModel {
    public static boolean addItem(ItemDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();


        String sql = "INSERT INTO item VALUES(?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getItemCode());
        ptm.setString(2, dto.getItemDetails());
        ptm.setString(3, dto.getItemPrice());
        ptm.setString(4, dto.getSupplierId());
        ptm.setString(5, dto.getWarrantyPeriod());


        return ptm.executeUpdate()>0;
    }
}
