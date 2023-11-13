package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DiliverController {
    public AnchorPane root;
    public TextField txtItemId;
    public TextField txtdiscount;
    public TextField txtQty;
    public JFXButton btnAddToBill;
    public Label lblNetTotal;
    public Label lbitemName;
    public Label lbitemPrice;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colTotal;

    public void btnAddToBillOnAction(ActionEvent event) {
    }

    public void btnPayOnAction(ActionEvent event) {
    }
}
