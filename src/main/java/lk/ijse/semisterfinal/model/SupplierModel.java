package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierModel {
    public static boolean addSuppliers(SupplierDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getSupId());
        ptm.setString(2, dto.getSupName());
        ptm.setString(3, dto.getSupItemName());

        return ptm.executeUpdate()>0;

    }
}
