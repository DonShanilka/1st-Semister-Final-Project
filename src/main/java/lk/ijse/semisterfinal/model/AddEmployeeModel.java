package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.CusromerDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<AddEmployeeDTO> getAllEmployee() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * employee FROM ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<AddEmployeeDTO> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new AddEmployeeDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return dtoList;
    }

    public static boolean updateEmployee(AddEmployeeDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE supplier SET supplier_name = ?, qty = ? WHERE supplier_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getEmployeeId());
        pstm.setString(2, dto.getEmployeeName());
        pstm.setString(3, dto.getEmpAddress());
        //pstm.setString(4, dto.getEmpAddress());
        //pstm.setString(5, dto.getEmpAddress());
        //pstm.setString(6, dto.getEmpAddress());

        return pstm.executeUpdate() > 0;
    }

    public static AddEmployeeDTO searchEmployee(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM employee WHERE customer_id = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        AddEmployeeDTO dto = null;

        if (resultSet.next()){
            String eid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String mobile = resultSet.getString(4);
            String date = resultSet.getString(5);
            String gender = resultSet.getString(6);
            String position = resultSet.getString(7);

            dto = new AddEmployeeDTO(eid,name,address,mobile,date,gender,position);
        }
        return dto;
    }

    public AddEmployeeDTO search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
