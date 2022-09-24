package Controller;


import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.PurchaseOrderBO;
import bo.custom.SaveOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.ItemDetails;
import tm.Carttm;
import model.Customer;
import model.Item;
import model.Order;
import tm.ItemDetailtm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Controller.CashierLoginFormController.firstnameofcashier;

public class PlaceCustomerOrderFormController {
    public Button btnLogOut;

    public JFXTextField txtSearchCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtCustomerTitle;
    public TextField txtPostalCode;
    public TextField txtNationalId;
    public ComboBox cmbItemName;
    public Text txtGrossAmount;
    public Text txtTotalDiscount;
    public Text txtNetAmount;



    public Button btnAddCustomer;
    public Button btnUpdateCustomer;
    public Label lblCustomerId;
    public JFXTextField txtId;
    public Label lblSetName;
    public ComboBox<String> cmbItemIds;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQTYOnHand;
    public TextField txtQty;
    public TableView<Carttm> tblItemDetail;
    public TableColumn code;
    public TableColumn description;
    public TableColumn qty;
    public TableColumn unitPrice;
    public TableColumn total;
    public Text txtTtl;
    public TableView<Carttm> tblCart;
    public TextField txtQtyOnHand;
    public TableView tblItemDetails;
    public TableColumn orderQTY;
    public TableColumn discount;
    public TableColumn price;
    public TextField txtQTYOnHand2;
    public TableColumn getPrice;
    public Text lblGrossAmount;
    public Text lblTotalDiscount;
    public Text lblTotalNetAmount;
    public AnchorPane placeCustomerOrderForm;
    public TableColumn colCode2;
    public TableColumn colDescription2;
    public TableColumn colOrderQTY;
    public TableColumn colDiscount;
    public TableColumn colPrice;
    public Button saveOrderId;
    public Label lblOrderID;
    public JFXButton saveOrder;

    int cartSelectedRowForRemove = -1;
    private final SaveOrderBO saveOrderBO = (SaveOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.SavedOrder);
    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);
    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes. PURCHASE_ORDER);


    public void initialize() throws SQLException, ClassNotFoundException {
        lblCustomerId.setText(saveOrderBO.setCustomerIDS());
        lblSetName.setText(firstnameofcashier);
        //lblOrderID.setText(saveOrderBO.setOrderIDS())
        lblOrderID.setText(saveOrderBO.setCustomerIDS());

        loadItemIds();


        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                //Item item = saveOrderBO.getItemForShow(newValue);
                Item item = itemBO.getItemForShow(newValue);
                ItemDetailtm i = new ItemDetailtm(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand());
                ArrayList<ItemDetailtm> temp1 = new ArrayList<>();
                temp1.add(i);

                colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
                colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
                colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

                tblItemDetails.setItems(FXCollections.observableArrayList(temp1));



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    public Item getItemForShow(String itemDesc) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE description = '" + itemDesc + "'").
                executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getDouble(6)
            );
        }else{
            return null;
        }
    }


    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemDAOImpl().getAllItemDescriptions();
        cmbItemIds.getItems().addAll(itemIds);
    }

    public void addNewCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                lblCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),
                txtPostalCode.getText(),txtNationalId.getText(),txtCity.getText(),txtProvince.getText(),txtCustomerTitle.getText()
        );

        if (saveOrderBO.add(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            initialize();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }


    public void selectIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtId.getText();

        Customer c1= saveOrderBO.getCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    void setData(Customer c){
        lblCustomerId.setText(c.getCustomerID());
        txtCustomerName.setText(c.getCustomerName());
        txtCustomerAddress.setText(c.getCustomerAddress());
        txtPostalCode.setText(c.getPostalCode());
        txtNationalId.setText(c.getNationalId());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtCustomerTitle.setText(c.getCustomerTitle());

    }

    public void newUpdateCustomers(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!lblCustomerId.getText().equals("") && !txtCustomerName.getText().equals("") && !txtCustomerAddress.getText().equals("") && !txtPostalCode.getText().equals("") && !txtNationalId.getText().equals("") && !txtCity.getText().equals("") && !txtProvince.getText().equals("") && !txtCustomerTitle.getText().equals("")) {

            Customer c1 = new Customer(
                    lblCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtPostalCode.getText(),
                    txtNationalId.getText(), txtCity.getText(), txtProvince.getText(), txtCustomerTitle.getText()
            );

            if (saveOrderBO.update(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update successfully..", ButtonType.OK).show();

                //lblCustomerId.clear();
                txtCustomerName.clear();
                txtCustomerAddress.clear();
                txtPostalCode.clear();
                txtNationalId.clear();
                txtCity.clear();
                txtProvince.clear();
                txtCustomerTitle.clear();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Not a successfully update customer details", ButtonType.OK).show();
            }

        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "please fill all fields and try again", ButtonType.OK).show();
        }
    }





    ObservableList <Carttm> obList= FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {

        if (!txtQtyOnHand.getText().equals("") ) {

            try {
                //Item item = itemBO.getItemByDescription(cmbItemIds.getSelectionModel().getSelectedItem());
                Item item = itemBO.getItemByDescription(cmbItemIds.getSelectionModel().getSelectedItem());

                if (obList.isEmpty()) {

                    double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQtyOnHand.getText());
                    double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQtyOnHand.getText()));
                    //double netPrice = price - totDiscount;

                    Carttm carttm = new Carttm(item.getItemCode(), item.getDescription(), Integer.parseInt(txtQtyOnHand.getText()), totDiscount, price);

                    if (item.getQtyOnHand() < carttm.getOrderQuantity() ) {

                        new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                        txtQtyOnHand.clear();
                    } else {

                        obList.add(carttm);
                        calculateCost();
                        txtQtyOnHand.clear();
                    }


                    colCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                    colDescription2.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
                    colOrderQTY.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
                    colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
                    colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                    tblCart.setItems(FXCollections.observableArrayList(obList));
                    //tblItemDetail.refresh();

                } else {
                    try {
                        for (Carttm c : obList) {
                            if (c.getItemCode().equals(item.getItemCode())) {

                                double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQtyOnHand.getText());
                                double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQtyOnHand.getText()));
                                //double netPrice = price - totDiscount;

                                if (item.getQtyOnHand() < c.getOrderQuantity() +(Integer.parseInt(txtQtyOnHand.getText()))) {
                                    new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                                    txtQtyOnHand.clear();
                                } else {
                                    c.setOrderQuantity(c.getOrderQuantity() + Integer.parseInt(txtQtyOnHand.getText()));
                                    c.setDiscount(c.getDiscount() + totDiscount);
                                    c.setPrice(c.getPrice() + price);

                                    calculateCost();
                                    tblItemDetail.refresh();
                                    txtQtyOnHand.clear();
                                }
                                return;

                            }
                        }
                        double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQtyOnHand.getText());
                        double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQtyOnHand.getText()));
                        //double netPrice = price - totDiscount;
                        Carttm carttm = new Carttm(item.getItemCode(), item.getDescription(), Integer.parseInt(txtQtyOnHand.getText()), totDiscount, price);

                        if (item.getQtyOnHand() < carttm.getOrderQuantity()) {
                            new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                            txtQtyOnHand.clear();
                        } else {
                            calculateCost();
                            obList.add(carttm);
                            txtQtyOnHand.clear();
                        }

                        colCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                        colDescription2.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
                        colOrderQTY.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
                        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
                        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                        tblCart.setItems(FXCollections.observableArrayList(obList));

                    } catch (Exception e) {
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            calculateCost();
        }else{
            new Alert(Alert.AlertType.WARNING, "Please enter order Quantity and try again..!").show();
        }

    }

    void calculateCost(){
        double ttl=0;
        double dis=0.0;
        double netTot = 0.0;
        for (Carttm tm:obList
        ) {
            ttl+=tm.getPrice();
            dis+=tm.getDiscount();
            netTot+=ttl-dis;

        }
        lblGrossAmount.setText(ttl+" /=");
        lblTotalDiscount.setText(dis+" /=");
        lblTotalNetAmount.setText(netTot+" /=");
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{

            Carttm c = obList.get(cartSelectedRowForRemove);
//
            Item i = saveOrderBO.getItem(c.getItemCode());
            if(Integer.parseInt(txtQTYOnHand2.getText()) > i.getQtyOnHand()){
                new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                txtQTYOnHand2.clear();
            }else{
                c.setOrderQuantity(Integer.parseInt(txtQTYOnHand2.getText()));
                double tot = (Integer.parseInt(txtQTYOnHand2.getText()))* i.getUnitPrice() * Integer.parseInt(i.getPackSize());
                c.setPrice(tot);
                double discount = (Integer.parseInt(txtQTYOnHand2.getText()))* i.getDiscount() * Integer.parseInt(i.getPackSize());
                c.setDiscount(discount);
                obList.remove(cartSelectedRowForRemove);
                obList.add(cartSelectedRowForRemove,c);
                calculateCost();
                tblCart.refresh();
                txtQTYOnHand2.clear();

            }
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully..").show();
        }
    }

    public void deleteItemOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{

            obList.remove(cartSelectedRowForRemove);


            calculateCost();

            colCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colDescription2.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
            colOrderQTY.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
            colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            tblCart.setItems(FXCollections.observableArrayList(obList));

            tblCart.refresh();

        }

    }

    public void cancelOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/PlaceCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        placeCustomerOrderForm.getChildren().clear();
        placeCustomerOrderForm.getChildren().add(load);
    }

    public void saveOrderForm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDetails> items = new ArrayList<>();
        for (Carttm c : obList) {
            items.add(new ItemDetails(
                    c.getItemCode(),
                    c.getItemDescription(),
                    c.getOrderQuantity(),
                    c.getDiscount(),
                    c.getPrice()
            ));
        }
        Order order = new Order(lblOrderID.getText(),txtNationalId.getText(),items);
        if(saveOrderBO.saveOrder(order.getOrderId(),order.getCustomerNIC(),order.getItem())){
            //saveOrderBO.saveIDTOTempTable(lblOrderID.getText());
            saveOrderBO.saveIDTOTempTable(lblOrderID.getText());
            new Alert(Alert.AlertType.CONFIRMATION, "Order Saved Successfully...!").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try again!").show();
        }
    }


    public void gotoSaveOrderFormController(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/SaveOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        placeCustomerOrderForm.getChildren().clear();
        placeCustomerOrderForm.getChildren().add(load);
    }

    public void goTodAshBordForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        placeCustomerOrderForm.getChildren().clear();
        placeCustomerOrderForm.getChildren().add(load);
    }
}

