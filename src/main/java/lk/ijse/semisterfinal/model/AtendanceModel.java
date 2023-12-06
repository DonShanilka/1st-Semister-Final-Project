package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AtendanceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtendanceModel {
    public static boolean addAttendance(AtendanceDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO attendance VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getDate());
            pstm.setString(2, dto.getEmployeeId());
            pstm.setString(3, dto.getEmployeeName());

            return pstm.executeUpdate() > 0;
        }
    }
}
