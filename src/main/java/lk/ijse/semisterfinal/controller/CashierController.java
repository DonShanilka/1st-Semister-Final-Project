package lk.ijse.semisterfinal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.dto.*;
import lk.ijse.semisterfinal.model.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CashierController {
    @FXML
    public TextField txtDiscount;
    public TextField paidAmount;
    public Label lblBalence;
    public TableColumn colDiscount;
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
    private OrderModel orderModel = new OrderModel();
    private BillModel billModel = new BillModel();

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        loadItemId();
        loadCustomerId();
        generateNextOrderId();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));
        //b.setCellValueFactory(new PropertyValueFactory<>("WarrantyPeriod"));

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
        System.out.println("in generate next id ");
        String orderId;
        try {
            String lastId = orderModel.getLastId();
            System.out.println("Last id is : " + lastId);
            if (lastId == null) {
                orderId = "O0001";
            } else {
                String[] ar = lastId.split("[O]");
                int digits = Integer.parseInt(ar[1]);
                digits++;
                String newId = String.format("O%04d", digits);
                orderId = newId;
            }
            System.out.println("Order id : " + orderId);
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
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int  qty = Integer.parseInt(txtQty.getText());
        double discount =qty * Double.parseDouble(txtDiscount.getText());
        double tot = (unitPrice * qty) - (discount * qty);
        System.out.println(discount);
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        System.out.println(obList.toString());

        if (!obList.isEmpty()) {
            System.out.println("in list");
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice - discount * qty;
                    System.out.println(tot);
                    System.out.println("Shanilka");
                    obList.get(i).setQty(String.valueOf(qty));
                    obList.get(i).setTotal(tot);

                    calculateTotal();
                    tblOrderCart.refresh();
                    return;
                }
            }
        }
        obList.add(new CartTm(code, description,unitPrice, qty, tot,discount, btn));

        tblOrderCart.setItems(obList);
        calculateTotal();
    }

    private void calculateBalance(){
        double payment = Double.parseDouble(paidAmount.getText());
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        double balance = payment - total;
        lblBalence.setText(String.valueOf("Rs : " + balance));

    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf("Rs : " + total));
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
        calculateBalance();

        try {
            String orderId = lblOrderId.getText();
            System.out.println("Order id: " + orderId);

            LocalDate date = LocalDate.parse(lblOrderDate.getText());
            String customerId = cmbCustomerId.getValue();

            List<CartTm> cartTmList = new ArrayList<>();

            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                CartTm cartTm = tblOrderCart.getItems().get(i);
                cartTmList.add(cartTm);

            }

            System.out.println("Place order from controller: " + cartTmList);

            PlaceOrderDto placeOrderDto = new PlaceOrderDto(orderId, date, customerId, cartTmList);

            boolean isSuccess = CashiyerModel.placeOrder(placeOrderDto);

            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtDiscountOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }


    /*public void reportOnAction(ActionEvent event) {
        throws JRException, SQLException {

            HashMap map = new HashMap<>();

            map.put("fee", tm.getParkingFee());
            map.put("space",tm.getSpaceNum());
            map.put("type", tm.getType());

            InputStream resourceAsStream =
                    getClass().getResourceAsStream("../report/parkingticket.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            compileReport,
                            map,
                            new JREmptyDataSource()
                    );
            JasperViewer.viewReport(jasperPrint, false);

        }

        public void mouseClicakAction(MouseEvent mouseEvent) {
            Integer index  = parkingmain_txt.getSelectionModel().getSelectedIndex() ;
            if(index <=-1){
                return;
            }
            tm.setDate(LocalDate.parse(parkingdate_txt.getCellData(index).toString()));
            tm.setParkingFee(Double.parseDouble(parkingfee_txt.getCellData(index).toString()));
            tm.setType(parkingtype_txt.getCellData(index).toString());
            tm.setSpaceNum(parkingspace_txt.getCellData(index).toString());

    }*/
}

