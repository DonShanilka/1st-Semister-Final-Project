package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AtendanceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AtendanceModel {
    public static boolean addAttendance(AtendanceDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO Attendance (Date, EmployeeId, Employeeame) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getDate());
            pstm.setString(2, dto.getEmployeeId());
            pstm.setString(3, dto.getEmployeeName());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean addAttendanceList(List <AtendanceDTO> attendanceDtoList) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO Attendance (Date, EmployeeId, EmployeeName) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false); // Start a transaction

            for (AtendanceDTO dto : attendanceDtoList) {
                pstm.setString(1, dto.getDate());
                pstm.setString(2, dto.getEmployeeId());
                pstm.setString(3, dto.getEmployeeName());

                pstm.addBatch();
            }

            int[] result = pstm.executeBatch();
            connection.commit(); // Commit the transaction

            // Check if all batches were executed successfully
            for (int i : result) {
                if (i <= 0) {
                    return false;
                }
            }

            return true;
        } finally {
            connection.setAutoCommit(true); // Reset to auto-commit mode
        }
    }
}
