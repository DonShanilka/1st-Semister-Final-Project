package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.dto.CusromerDTO;
import lk.ijse.semisterfinal.dto.ItemDTO;
import lk.ijse.semisterfinal.dto.PlaceOrderDto;
import lk.ijse.semisterfinal.model.BillModel;
import lk.ijse.semisterfinal.model.CashiyerModel;
import lk.ijse.semisterfinal.model.CustomerModel;
import lk.ijse.semisterfinal.model.ItemModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CashierController {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblOrderDate;
    @FXML
    private JFXComboBox<String> cmbCustomerId;
    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox<String> cmbItemCode;
    @FXML
    private Label lblItemName;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private TextField txtQty;
    @FXML
    private TableView <CartTm> tblOrderCart;
    @FXML
    private TableColumn<?, ?> colItemCode;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn <?, ?> colUnitPrice;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private JFXButton btnAddToCart;
    @FXML
    private Label lblNetTotal;
    @FXML
    private TableColumn <?,?> colQty;

    private ObservableList<CartTm> ObList = FXCollections.observableArrayList();
    private ItemModel itemModel = new ItemModel();
    private CustomerModel customerModel = new CustomerModel();
    private CashiyerModel cashiyerModel = new CashiyerModel();
    private BillModel billModel = new BillModel();

    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        loadItemId();
        loadCustomerId();
        //generateNextOrderId();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        //colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

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
            String orderId = CashiyerModel.generateNextOrderId();
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
            lblQtyOnHand.setText(String.valueOf(itemDTO.getItemQty()));
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
            lblQtyOnHand.setText(String.valueOf(dto.getItemQty()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    @FXML
    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        String code = cmbItemCode.getValue();
        String description = lblItemName.getText();
        int  qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double tot = qty * unitPrice;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            System.out.println("in list");
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(i)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;
                    System.out.println("test");
                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(tot);


                    tblOrderCart.refresh();
                    calculateTotal();
                    return;
                }
            }
        }


        obList.add(new CartTm(code, description, qty, unitPrice, tot, btn));

        tblOrderCart.setItems(ObList);
        calculateTotal();
    }


    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }

    private void setRemoveBtnAction(Button btn) {
            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int focusedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();

                    obList.remove(focusedIndex);
                    tblOrderCart.refresh();
                    calculateTotal();
                }
            });
        }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        LocalDate date = LocalDate.parse(lblOrderDate.getText());
        String customerId = cmbCustomerId.getValue();

        List<CartTm> cartTmList = new ArrayList<>();
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTm cartTm = obList.get(i);

            cartTmList.add(cartTm);
        }

        System.out.println("Place order form controller: " + cartTmList);
        var placeOrderDto = new PlaceOrderDto(orderId, date, customerId, cartTmList);
        try {
            boolean isSuccess = billModel.placeOrder(placeOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

       /*var orderDto = new OrderDto(txtOrderId.getText(), lblOrderDate.getText(), cmbGuardian_Id.getValue());
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

