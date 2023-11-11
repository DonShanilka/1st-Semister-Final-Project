package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class EmployeeDashbordControlle {
    public JFXButton item;
    public JFXButton supplier;
    public JFXButton deliver;
    public JFXButton customer;
    public AnchorPane root;
    public JFXButton supplier1;


    public void initialize() throws IOException {
        DashboardOnAction(null);
    }
    void setForm(String form) throws IOException {
        String[] formArray = {"/view/AddItem.fxml","/view/Customer.fxml", "/view/Diliver.fxml", "/view/Supplier.fxml"};

        JFXButton[] btnArray = {customer,deliver,item,supplier};
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(form)));
        root.getChildren().clear();
        root.getChildren().add(load);
        for (int i = 0; i < formArray.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #ffffff; -fx-font-size: 15");

            if (form.equals(formArray[i])){
                btnArray[i].setStyle("-fx-background-color: #ffffff; -fx-text-fill: #001e79");
            }
        }
    }


    public void EmpItemOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddItem.fxml");
    }

    public void EmpCustomerOnAction(ActionEvent event) throws IOException {
        setForm("/view/Customer.fxml");
    }

    public void EmpDeliverOnAction(ActionEvent event) throws IOException {
        setForm("/view/Diliver.fxml");
    }

    public void DashboardOnAction(ActionEvent event) throws IOException {
        setForm("/view/employeeDashbord.fxml.fxml");
    }

    public void EmpSuppliersOnAction(ActionEvent event) throws IOException {
        setForm("/view/Supplier.fxml");
    }


    public void CustomerOnAction(ActionEvent event) {

    }
}
