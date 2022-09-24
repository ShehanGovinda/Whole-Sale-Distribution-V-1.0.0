package Controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.PurchaseOrderBO;
import bo.custom.SaveOrderBO;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.SavedOrder;
import tm.ItemDetailtm;
import model.ItemPackage;
import tm.OrderIDStm;
import tm.SavedOrderDetailsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveOrderFormController {

    public TableColumn colTotal;
    public TableColumn colDiscount;
    public TableColumn colQTY;
    public TableColumn colDescription;
    public TableColumn colItemCode;
    public TableView tblItemDetails;
    public Button btnPlaceOrder;
    public TextField txtCash;
    public Label lblBalance;
    public Label lblNetAmount;
    public Label lblDiscount;
    public Label lblGrossAmount;
    public Button btnUpdate;
    public TextField txtUpdateQTY;
    public Button btnDelete;
    public Button btnAddToCart;
    public TextField txtQTYForSell;
    public Label lblNIC;
    public Label lblCustomerID;
    public TextField txtQTYOnHand;
    public TextField txtUnitPrice;
    public TextField txtPackSize;
    public TextField txtItemCode;
    public ComboBox <String> cmbItemIds;
    public TableView<OrderIDStm> tblOIDAndNIC;
    public ComboBox<String> cmbOrderID;
    public TableColumn colOrderID;
    public TableColumn colNIC;
    public TextField txtTime;
    public TextField txtDate;
    public AnchorPane savedFormContext;
    ArrayList<SavedOrder> nic=null;

    int cartSelectedRow = -1;

    private final SaveOrderBO saveOrderBO = (SaveOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.SavedOrder);
    private final PurchaseOrderBO saveItem = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);
    private final CustomerBO saveCustomer = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    ObservableList<SavedOrderDetailsTM> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime();
//
        lblCustomerID.setText(saveCustomer.generateNewID());
        lblNIC.setText(saveOrderBO.setOrderIDS());

        tblItemDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRow = (int) newValue;
        });

        List temp = saveItem.getAllItemDescriptions();
        cmbItemIds.getItems().addAll(temp);
//
        //setOrderIdAndNicToTable(saveOrderBO.getAllOrderIDSWithNIC());
        setOrderIdAndNicToTable(saveOrderBO.getAllOrderIDSWithNIC());

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                Item item = itemBO.getItemByDescription(newValue);
                ItemDetailtm i = new ItemDetailtm(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand());

                txtItemCode.setText(i.getItemCode());
                txtPackSize.setText(i.getPackSize());
                txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
                txtQTYOnHand.setText(String.valueOf(i.getQtyOnHand()));


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        cmbOrderID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                obList.clear();
                setItemsToTable( newValue);
                //
                nic = saveOrderBO.getNIC(newValue);
                Customer cust = saveOrderBO.getCustomer(nic);
                lblCustomerID.setText(cust.getCustomerID());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }



    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }



    private void setItemsToTable(String newValue) throws SQLException, ClassNotFoundException {
        //
        //ArrayList<SavedOrderDetailsTM> s=saveOrderBO.getOrderDetails(newValue,lblNIC);
        ArrayList<SavedOrderDetailsTM> s=saveOrderBO.getOrderDetails(newValue,lblNIC);
        s.forEach(e->{
            obList.add(new SavedOrderDetailsTM(e.getItemCode(),e.getDescription(),e.getQtyForSell(),e.getDiscount(),e.getTotal()));
        });
        tblItemDetails.setItems(obList);
        calculate(obList);
        setDataToTable();
    }


    public void setDataToTable(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qtyForSell"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }




    private void calculate(ObservableList<SavedOrderDetailsTM> obList){
        double discount =0.0 ;
        double total =0.0 ;
        double netTotal =0.0 ;
        for (SavedOrderDetailsTM s : obList){
            discount+=s.getDiscount();
            total+=s.getTotal();
        }
        netTotal=total-discount;
        lblGrossAmount.setText(String.valueOf(total));
        lblDiscount.setText(String.valueOf(discount));
        lblNetAmount.setText(String.valueOf(netTotal));
    }


    private void setOrderIdAndNicToTable(ArrayList<SavedOrder> orderIdAndNic) {
        ObservableList<OrderIDStm> savedOIDAndNic= FXCollections.observableArrayList();
        orderIdAndNic.forEach(e->{
            OrderIDStm savedOidAndNicDetailsTM=new OrderIDStm(e.getoId(),e.getNIC());
            int i=0;
            do {
                if(savedOIDAndNic.size()==0){
                    savedOIDAndNic.add(savedOidAndNicDetailsTM);
                    //i++;
                    break;
                }else if(isExists(savedOidAndNicDetailsTM,savedOIDAndNic)){

                }else{
                    savedOIDAndNic.add(savedOidAndNicDetailsTM);
                }
                i++;
            }while(i<savedOIDAndNic.size());
        });

        for (int i = 0; i < savedOIDAndNic.size(); i++) {
            cmbOrderID.getItems().add(savedOIDAndNic.get(i).getOrderID());
        }
        tblOIDAndNIC.setItems(savedOIDAndNic);
    }

    private boolean isExists(OrderIDStm savedOidAndNicDetailsTM,ObservableList<OrderIDStm> savedOIDAndNic){
        for (int i = 0; i <savedOIDAndNic.size() ; i++) {
            if(savedOidAndNicDetailsTM.getOrderID().equals(savedOIDAndNic.get(i).getOrderID())){
                return true;
            }
        }
        return false;
    }
    public void goToAddToCartOnAction(ActionEvent actionEvent) {
        addToCartOnAction(actionEvent);
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        ItemPackage d = new ItemPackage(cmbOrderID.getSelectionModel().getSelectedItem(),txtDate.getText(),lblCustomerID.getText(),lblNetAmount.getText());
//
        if(saveOrderBO.saveOrdertoDbTable(d)){}
        new OrderDAOImpl().saveOrderToOrderDetailTable(cmbOrderID.getSelectionModel().getSelectedItem(),obList);
        saveOrderBO.updateItemTable(obList);
        //cancelOrderOnAction(actionEvent);
//
        if(saveOrderBO.deleteOrderFromSavedOrderTable(cmbOrderID.getSelectionModel().getSelectedItem())){

            URL resource = getClass().getResource("../views/SaveOrderForm.fxml");
            Parent load = FXMLLoader.load(resource);
            savedFormContext.getChildren().clear();
            savedFormContext.getChildren().add(load);
        }

        new Alert(Alert.AlertType.CONFIRMATION, "Order Placed Successfully..!").show();


    }

    public void cancelOrderOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        //
        if(saveOrderBO.deleteOrderFromSavedOrderTable(cmbOrderID.getSelectionModel().getSelectedItem())){
            new Alert(Alert.AlertType.WARNING, "Do you Want to Cancel Order...!").showAndWait();

            URL resource = getClass().getResource("../views/SaveOrderForm.fxml");
            Parent load = FXMLLoader.load(resource);
            savedFormContext.getChildren().clear();
            savedFormContext.getChildren().add(load);
        }else{
            //new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
        }
    }

    public void getBalance(ActionEvent actionEvent) {
        txtQTYForSell.setEditable(false);
        btnAddToCart.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        double net = Double.parseDouble(txtCash.getText())-Double.parseDouble(lblNetAmount.getText());
        lblBalance.setText(String.valueOf(net)+" /=");
        btnPlaceOrder.setDisable(false);
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{

            SavedOrderDetailsTM c = obList.get(cartSelectedRow);
//
            Item i = new ItemDAOImpl().getItem(c.getItemCode());
            if(Integer.parseInt(txtUpdateQTY.getText()) > i.getQtyOnHand()){
                new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                txtUpdateQTY.clear();
            }else{
                c.setQtyForSell(Integer.parseInt(txtUpdateQTY.getText()));
                double tot = (Integer.parseInt(txtUpdateQTY.getText()))* i.getUnitPrice() * Integer.parseInt(i.getPackSize());
                c.setTotal(tot);
                double discount = (Integer.parseInt(txtUpdateQTY.getText()))* i.getDiscount() * Integer.parseInt(i.getPackSize());
                c.setDiscount(discount);
                obList.add(cartSelectedRow,c);
                obList.remove(cartSelectedRow);

                calculate(obList);
                tblItemDetails.refresh();
                txtUpdateQTY.clear();
            }

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        if (cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Do you want to delete Order Item..",ButtonType.OK).showAndWait();
            obList.remove(cartSelectedRow);

            calculate(obList);

            setDataToTable();
            tblItemDetails.setItems(FXCollections.observableArrayList(obList));

        }
    }


    public void addToCartOnAction(ActionEvent actionEvent) {
        if (!txtQTYForSell.getText().equals("") ) {
            try {
//
                Item item = itemBO.getItemByDescription(cmbItemIds.getSelectionModel().getSelectedItem());

                if (obList.isEmpty()) {
                    double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQTYForSell.getText());
                    double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQTYForSell.getText()));
                    //double netPrice = price - totDiscount;
//
                    SavedOrderDetailsTM carttm = new SavedOrderDetailsTM(item.getItemCode(), item.getDescription(), Integer.parseInt(txtQTYForSell.getText()), totDiscount, price);

                    if (item.getQtyOnHand() < carttm.getQtyForSell()) {
                        new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                        txtQTYForSell.clear();
                    } else {

                        obList.add(carttm);
                       /* if(new OrderController().saveOrderByObject(cmbOrderID.getSelectionModel().getSelectedItem(),lblNIC.getText(),carttm)){

                        }*/
                        calculate(obList);
                        txtQTYForSell.clear();
                    }

                    setDataToTable();
                    tblItemDetails.setItems(obList);
                    tblItemDetails.refresh();

                } else {
                    try {
                        for (SavedOrderDetailsTM c : obList) {
                            if (c.getItemCode().equals(item.getItemCode())) {

                                double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQTYForSell.getText());
                                double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQTYForSell.getText()));


                                if (item.getQtyOnHand() < c.getQtyForSell() +(Integer.parseInt(txtQTYForSell.getText()))) {
                                    new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                                    txtQTYForSell.clear();
                                } else {
                                    int q =c.getQtyForSell() + Integer.parseInt(txtQTYForSell.getText());
                                    double d = c.getDiscount() + totDiscount;
                                    double t = c.getTotal() + price;
                                    c.setQtyForSell(c.getQtyForSell() + Integer.parseInt(txtQTYForSell.getText()));
                                    c.setDiscount(c.getDiscount() + totDiscount);
                                    c.setTotal(c.getTotal() + price);
                                   /* System.out.println(item.getItemCode());
                                    System.out.println(lblNIC.getText());
                                    if(new OrderController().updateSavedOrder(lblNIC.getText(),item.getItemCode(),q,d,t)){

                                    }*/
                                    setDataToTable();
                                    calculate(obList);
                                    tblItemDetails.refresh();
                                    txtQTYForSell.clear();
                                }
                                return;

                            }
                        }
                        double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQTYForSell.getText());
                        double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQTYForSell.getText()));
                        //double netPrice = price - totDiscount;
                        SavedOrderDetailsTM carttm = new SavedOrderDetailsTM(item.getItemCode(), item.getDescription(), Integer.parseInt(txtQTYForSell.getText()), totDiscount, price);

                        if (item.getQtyOnHand() < carttm.getQtyForSell()) {
                            new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                            txtQTYForSell.clear();
                        } else {
                            /*if(new OrderController().saveOrderByObject(cmbOrderID.getSelectionModel().getSelectedItem(),lblNIC.getText(),carttm)){

                            }*/
                            calculate(obList);
                            obList.add(carttm);
                            txtQTYForSell.clear();
                        }

                        setDataToTable();
                        tblItemDetails.setItems(obList);

                    } catch (Exception e) {
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            calculate(obList);
        }else{
            new Alert(Alert.AlertType.WARNING, "Please enter order Quantity and try again..!").show();
        }
    }



    public void goToPlaceCustomerOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/PlaceCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        savedFormContext.getChildren().clear();
        savedFormContext.getChildren().add(load);
    }
}
