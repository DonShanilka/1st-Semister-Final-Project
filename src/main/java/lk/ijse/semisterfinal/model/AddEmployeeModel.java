package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeModel {
    public static boolean addEmployee(AddEmployeeDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getEmployeeId());
        ptm.setString(2, dto.getEmployeeName());
        ptm.setString(3, dto.getEmpAddress());
        ptm.setInt(4, dto.getEmployeePhone());
        ptm.setDate(5, Date.valueOf(dto.getEmpDate()));
        ptm.setString(6, dto.getEmpPosition());

        return ptm.executeUpdate()>0;

    }
}
