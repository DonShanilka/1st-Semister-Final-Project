package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<SupplierDTO> loadAllSupplier() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT*FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierDTO> supidlist = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            supidlist.add(new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }

        return supidlist;
    }
}
