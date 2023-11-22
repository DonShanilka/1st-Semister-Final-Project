package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddEmployeeModel {
    public static boolean addEmployee(AddEmployeeDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getEmployeeId());
        ptm.setString(2, dto.getEmployeeName());
        ptm.setString(3, dto.getEmpAddress());
        ptm.setInt(4, dto.getEmployeePhone());
        ptm.setString(5, dto.getEmpDate());
        ptm.setString(6, dto.getEmployeeGender());
        ptm.setString(7, dto.getEmpPosition());

        return ptm.executeUpdate()>0;

    }

    /*public List<AddEmployeeDTO> getAllEmployee() {
    }*/

    public static boolean updateEmployee(SupplierDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE supplier SET supplier_name = ?, qty = ? WHERE supplier_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getSupId());
        pstm.setString(2, dto.getSupName());
        pstm.setInt(3, dto.getSupqty());

        return pstm.executeUpdate() > 0;
    }
}
