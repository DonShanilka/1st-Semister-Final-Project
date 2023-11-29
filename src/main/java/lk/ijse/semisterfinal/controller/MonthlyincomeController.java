package lk.ijse.semisterfinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyincomeController {

    @FXML
    private AreaChart<?, ?> incomeDataChart;

    @FXML
    private Label lblTotalInCome;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalUnits;

    @FXML
    private BarChart<?, ?> orderDataChart;

    @FXML
    private AnchorPane root1;

    CartTm cartTm = new CartTm();
    PlaceOrderDto placeOrderDto = new PlaceOrderDto();


    public void dashBordTotalOrders() throws SQLException {
        Connection connection = DbConnetion.getInstance().connection;
        //DbConnetion dbConnetion = new DbConnetion();

        String sql = "SELECT COUNT(order_id) FROM orders";

        String totalOrders = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalOrders = resultSet.getString("COUNT(order_id)");
            }
            lblTotalOrders.setText(totalOrders);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dashBordTotalInCome() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        //DbConnetion dbConnetion = new DbConnetion();

        String sql = "SELECT SUM(unit_price) FROM order_detail";

        double totalInCome = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalInCome = resultSet.getDouble("SUM(unit_price)");

            }
            lblTotalInCome.setText("Rs. " + String.valueOf(totalInCome));

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void dashBordTotalUnits() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        DbConnetion dbConnetion = new DbConnetion();

        String sql = "SELECT SUM(qty) FROM order_detail";
        connection = dbConnetion.connection;

        int totalUnits = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalUnits = resultSet.getInt("SUM(qty)");

            }
            lblTotalUnits.setText(String.valueOf(totalUnits));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void incomeChart() throws SQLException {
        incomeDataChart.getData().clear();

        String sql = "SELECT SUM(unit_price) FROM order_detail";
        Connection connection = DbConnetion.getInstance().getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data(resultSet.getString(2), resultSet.getInt(1)));
            }
            incomeDataChart.getData().add(chart);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws SQLException {
        incomeChart();
        dashBordTotalInCome();
        dashBordTotalUnits();
        dashBordTotalOrders();
    }

}
