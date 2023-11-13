package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CashierTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.CashiyerModel;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CashierController {
    public AnchorPane root;
    public TextField txtItemId;
    public TextField txtdiscount;
    public TextField txtQty;
    public JFXButton btnAddToBill;
    public Label lblNetTotal;
    public Label lbitemName;
    public Label lbitemPrice;
    public TableColumn <?, ?> colItemId;
    public TableColumn <?, ?> colItemName;
    public TableColumn <?, ?> colUnitPrice;
    public TableColumn <?, ?> colQty;
    public TableColumn <?, ?> colDiscount;
    public TableColumn <?, ?> colTotal;
    public TableView<CashierTm> tbCashiyer;
    public Label lblOrderId;
    public JFXComboBox cmbCustomerId;


    private CustomerModel customerModel = new CustomerModel();
    private ItemModel itemModel = new ItemModel();
    private CashiyerModel orderModel = new CashiyerModel();

    private ObservableList<CashierTm> obList = FXCollections.observableArrayList();

    public void btnAddToBillOnAction(ActionEvent event) {
        String code = txtItemId.getText();
        String description = lbitemName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lbitemPrice.getText());
        double tot = unitPrice * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            for (int i = 0; i < tbCashiyer.getItems().size(); i++) {
                if (colItemId.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(tot);

                    calculateTotal();
                    tbCashiyer.refresh();
                    return;
                }
            }
        }
        Object cartTm = new CashierTm (code, description, qty, unitPrice, tot, btn);

        obList.add((CashierTm) cartTm);

        tbCashiyer.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }

    public void btnPayOnAction(ActionEvent event) {
    }

    public void initialize() {
        setCellValueFactory();
        generateNextOrderId();
        //setDate();
        loadCustomerIds();
        loadItemCodes();
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        //colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {
        try {
            String orderId = CashiyerModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ItemDTO> itemDtos = ItemModel.loadAllItems();

            for (ItemDTO dto : itemDtos) {
                obList.add(dto.getItemCode());
            }
            txtItemId.setText(obList.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CusromerDTO> idList = CustomerModel.getAllCustomer();

            for (CusromerDTO dto : idList) {
                obList.add(dto.getTxtCustId());
            }

            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //private void setDate() {
//        LocalDate now = LocalDate.now();
    //    lblOrderDate.setText(String.valueOf(LocalDate.now()));
    //}


    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tbCashiyer.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tbCashiyer.refresh();
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tbCashiyer.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }

    public void cmbCustomerOnAction(ActionEvent event) {

    }
}
