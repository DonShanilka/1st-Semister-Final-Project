package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.Tm.CashierTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.model.CashiyerModel;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CashierController {

    public AnchorPane pane;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox <String> cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox <String> cmbItemCode;
    public Label lblItemName;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView<Object> tblOrderCart;
    public TableColumn<?, ?> colItemCode;
    public TableColumn<?, ?> colDescription;
    public TableColumn<?, ?> colUnitPrice;
    public TableColumn<?, ?> colTotal;
    public TableColumn<?, ?> colAction;
    public JFXButton btnAddToCart;
    public Label lblNetTotal;
    public TableColumn colQty;

    private SortedList<Object> obList = FXCollections.observableArrayList().sorted();
    private ItemModel itemModel = new ItemModel();
    private CustomerModel customerModel = new CustomerModel();
    private CashiyerModel cashiyerModel = new CashiyerModel();

    private ObservableList<CartTm> observableList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        loadItemId();
        loadCustomerId();
        //generateNextOrderId();
        //cmbItemCode();
        //cmbCustomerId();
    }


    private void loadItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> idList = itemModel.loadAllItems();

            for (ItemDTO dto : idList) {
                obList.add(dto.getItemCode());
            }

            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CusromerDTO> idList = customerModel.getAllCustomer();
            for (CusromerDTO dto : idList) {
                obList.add(dto.getTxtCustId());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextOrderId() {
        try {
            String orderId = cashiyerModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    public void cmbCustomerId(ActionEvent actionEvent) {
        String id = cmbCustomerId.getValue();

        try {
            CusromerDTO cusromerDTO = customerModel.searchCustomer(id);
            lblCustomerName.setText(cusromerDTO.getTxtCustName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbItemCode(ActionEvent actionEvent) {
        String id = cmbItemCode.getValue();

        try {
            ItemDTO itemDTO = itemModel.searchItemId(id);
            lblItemName.setText(itemDTO.getItemDetails());
            lblUnitPrice.setText(String.valueOf(itemDTO.getItemPrice()));
            //lblQtyOnHand.setText(itemDTO.);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();

        try {
            CusromerDTO dto = CustomerModel.searchCustomer(id);
            lblCustomerName.setText(dto.getTxtCustName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbItemOnAction(ActionEvent event) {
        String id = cmbItemCode.getValue();

        try {
            ItemDTO dto = ItemModel.searchItemId(id);
            lblItemName.setText(dto.getItemDetails());
            lblUnitPrice.setText(String.valueOf(dto.getItemPrice()));
            //lblQtyOnHand.setText(String.valueOf(dto.getItemQty()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtQtyOnAction(ActionEvent event) {

    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String code = cmbItemCode.getValue();
        String description = lblItemName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double tot = unitPrice * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    //obList.get(i).set(qty);
                    //obList.get(i).setTot(tot);

                    //calculateTotal();
                    tblOrderCart.refresh();
                    return;
                }
            }
        }
        var cartTm = new CartTm(code, description, qty, unitPrice, tot);

        obList.add(cartTm);

        tblOrderCart.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }


    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }
    private void setRemoveBtnAction(Button btn) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
//        String orderId = lblOrderId.getText();
//        LocalDate date = LocalDate.parse(lblOrderDate.getText());
//        String service_id = cmbService_id.getValue();
//
//        List<CartTm> cartTmList = new ArrayList<>();
//        for (int i = 0; i < tblOrder.getItems().size(); i++) {
//            CartTm cartTm = obList.get(i);
//
//            cartTmList.add(cartTm);
//        }

       /* var orderDto = new OrderDto(txtOrderId.getText(), lblOrderDate.getText(), cmbGuardian_Id.getValue());
        var oService = new OrderServiceDto(txtOrderId.getText(), lblOrderDate.getText(), cmbService_id.getValue());

        try {
            if (placeOrder.placeAOrder(orderDto, oService)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order saved!!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


    }
}