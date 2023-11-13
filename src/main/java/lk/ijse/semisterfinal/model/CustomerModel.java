package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.CusromerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static boolean AddCustomer(CusromerDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();


        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
        PreparedStatement ptm = connection.prepareStatement(sql);

        ptm.setString(1, dto.getTxtCustId());
        ptm.setString(2, dto.getTxtCustAddress());
        ptm.setString(3, dto.getTxtCustName());
        ptm.setString(4, dto.getTxtCustMobile());
        ptm.setString(5, dto.getTxtCustitemId());
        ptm.setString(6, dto.getTxtCustPayment());

        return ptm.executeUpdate()>0;
    }

    public static boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE customer_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }

    public static List<CusromerDTO> getAllCustomer() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT customer_id,customer_name,customer_address,customer_mobile,payment,item_id FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CusromerDTO> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new CusromerDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;
    }

}
