package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.dto.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static boolean addItem(ItemDTO dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO item VALUES(?,?,?,?,?,?)";

        PreparedStatement ptm = connection.prepareStatement(sql);
        ptm.setString(1, dto.getItemCode());
        ptm.setString(2, dto.getItemDetails());
        ptm.setDouble(3, dto.getItemPrice());
        ptm.setString(4, dto.getSupplierId());
        ptm.setString(5, dto.getWarrantyPeriod());
        ptm.setString(6, String.valueOf(dto.getItemQty()));

        boolean isSaved = ptm.executeUpdate()>0;
        return isSaved;
    }

    public static boolean deleteItem(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM item WHERE item_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<ItemDTO> loadAllItems() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<ItemDTO> itemList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            var dto =new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6)
            );
            itemList.add(dto);
        }

        return itemList;
    }

    public static ArrayList<ItemDTO> getAllItem() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM Item";

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
                            resultSet.getString(5),
                            resultSet.getInt(6)
                    )
            );
        }
        return dtoList;
    }

    public static ItemDTO searchItemId(String id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM item WHERE item_code = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        ItemDTO dto = null;

        if (resultSet.next()){
            String item_code = resultSet.getString(1);
            String item_name = resultSet.getString(2);
            double item_price = Double.parseDouble(resultSet.getString(3));
            String sup_id = resultSet.getString(4);
            String warranty = resultSet.getString(5);
            String qty = String.valueOf(resultSet.getInt(6));

            dto = new ItemDTO(item_code,item_name,item_price,sup_id,warranty,qty);
        }
        return dto;
    }

    public static boolean updateItem(List<CartTm> cartTmList) throws SQLException {
        for(CartTm tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getItem_code(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }

    public static boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE item SET qty = qty - ? WHERE item_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0;
    }

}
