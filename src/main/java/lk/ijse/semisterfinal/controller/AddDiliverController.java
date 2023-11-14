package lk.ijse.semisterfinal.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDiliverController {
    public AnchorPane rootNode;
    public BorderPane borderPane;
    public AnchorPane sliderAnchor;
    public TextField txtOrderId;
    public TextField txtOrderMobile;
    public TextField txtItemId;
    public TextField txtOrderAddress;
    public TextField txtCustitemIdOrder;
    public TableView orderTable;
    public TableColumn tbOrderId;
    public TableColumn tbOrderName;
    public TableColumn tbOrderAddress;
    public TableColumn tbOrderMobile;
    public TableColumn tbOrderPayment;
    public TableColumn tbOrderItemId;
    public Label addOrder;
    public Label backOrder;
    @FXML
    private Label back;

    @FXML
    private AnchorPane borderpane;

    @FXML
    private Label get;

    public void OrderDeleteOnAction(ActionEvent event) {
    }

    public void OrderAddOnAction(ActionEvent event) {
    }

    public void OrderUpdateOnAction(ActionEvent event) {
    }
}
