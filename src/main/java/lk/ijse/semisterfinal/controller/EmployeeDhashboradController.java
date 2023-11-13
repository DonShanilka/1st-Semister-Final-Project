package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class EmployeeDhashboradController {


    public JFXButton empSupplier;
    public JFXButton empDeliver;
    public JFXButton empCustomer;
    public JFXButton cashier;
    public AnchorPane rootNode2;
    public AnchorPane rootNode1;
    public JFXButton cashier1;
    public Pane root;
    public JFXButton empItem;

    public void initialize() throws IOException {
        CashierOnAction(null);
    }
    void setForm(String form) throws IOException {
        String[] formArray = {"/view/Cashier.fxml","/view/AddCustomer.fxml", "/view/AddItem.fxml", "/view/AddDiliver.fxml", "/view/viewSuppler.fxml"};

        JFXButton[] btnArray = {cashier,empCustomer,empItem,empDeliver,empSupplier};
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(form)));
        root.getChildren().clear();
        root.getChildren().add(load);
        for (int i = 0; i < formArray.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #ffffff; -fx-font-size: 12");

            if (form.equals(formArray[i])){
                btnArray[i].setStyle("-fx-background-color: #ffffff; -fx-text-fill: #040082");
            }
        }
    }
    public void empSupplierOnAction(ActionEvent event) throws IOException {
        setForm("/view/viewSuppler.fxml");

    }

    public void empDelierOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddDiliver.fxml");
    }

    public void CustomerOnAction(ActionEvent event) throws IOException {
        setForm("/view/AddCustomer.fxml");
    }

    public void CashierOnAction(ActionEvent event) throws IOException {
        setForm("/view/Cashier.fxml");
    }

    public void itemOnActionEmp(ActionEvent event) throws IOException {
        setForm("/view/AddItem.fxml");
    }
}
