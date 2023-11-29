package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.dto.PlaceOrderDto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMainDashbordController{
    public JFXButton monthlyIncome;

    public JFXButton item;
    public JFXButton Employee;
    public JFXButton supplier;
    public JFXButton deliver;

    public JFXButton customer;

    public AnchorPane root;
    public AnchorPane root1;
    public JFXButton cashiyer;

    @FXML
    public BarChart <?,?> orderDataChart;
    @FXML
    public Label lblTotalOrders;
    @FXML
    public Label lblTotalUnits;
    @FXML
    public Label lblTotalInCome;
    @FXML
    public AreaChart <?,?> incomeDataChart;


    public void initialize() throws IOException {
        monthlyIncomeOnAction(null);
    }
    void setForm(String form) throws IOException {
        String[] formArray = {"/view/AddCustomer.fxml","/view/Diliver.fxml", "/view/addEmployee.fxml", "/view/AddItem.fxml", "/view/AddSupplier.fxml", "/view/monthlyincome.fxml","/view/Cashier.fxml"};

        JFXButton[] btnArray = {customer,deliver,Employee,item,supplier,monthlyIncome,cashiyer};
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(form)));
        root1.getChildren().clear();
        root1.getChildren().add(load);
        for (int i = 0; i < formArray.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #380047; -fx-font-size: 15; -fx-text-fill: #fd8800");

            if (form.equals(formArray[i])){
                btnArray[i].setStyle("-fx-background-color: #eed5ff; -fx-text-fill: #380047");
            }
        }
    }
    public void monthlyIncomeOnAction(ActionEvent event) throws IOException {
        setForm("/view/monthlyincome.fxml");
    }

    public void itemOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddItem.fxml");
    }

    public void EmployeeOnAction(ActionEvent event) throws IOException {
        setForm("/view/addEmployee.fxml");
    }

    public void SupplierOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddSupplier.fxml");
    }

    public void DeliverOnAction(ActionEvent event) throws IOException {
        setForm("/view/Diliver.fxml");
    }

    public void CustomerOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddCustomer.fxml");
    }

    public void CashiyerOnAction(ActionEvent event) throws IOException {
        setForm("/view/Cashier.fxml");
    }

}
