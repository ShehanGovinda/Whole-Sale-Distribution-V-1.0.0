package Controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.ItemDAOImpl;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashBoardFormController {
    public TextField txtPackSize;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtDiscount;
    public TextField txtQTYOnHand;
    public Label lblItemCode;
    public JFXButton btnAddNewItem;
    public TextField txtModifyPackSize;
    public TextField txtModifyDescription;
    public TextField txtModifyUnitPrice;
    public TextField txtModifyDiscount;
    public TextField txtModifyQYTOnHand;
    public JFXButton btnUpdateItemDetails;
    public Label lblModifyItemCode;
    public JFXTextField txtModifyId;
    public TextField txtDeletePackSize;
    public TextField txtDeleteDescription;
    public TextField txtDeleteUnitPrice;
    public TextField txtDeleteDiscount;
    public TextField txtDeleteQTYOnHand;
    public JFXButton btnDeleteItemDetails;
    public Label lblDeleteItemCode;
    public JFXTextField txtDeleteId;
    public AnchorPane AdminDashBordForm;
    public TableView tblAnnualIncome;
    public TableColumn colANNOID;
    public TableColumn colCustID;
    public TableColumn colAnnualDiscount;
    public TableColumn colAnnualPrice;
    public ComboBox cmbYearForAnnualIncome;
    public Label lblAnnualIncome;
    public TableView tblMonthlyIncome;
    public TableColumn colOIDForMonthly;
    public TableColumn colCIDForMonthly;
    public TableColumn colOTimeForMonthly;
    public TableColumn colTDiscountForMonthly;
    public TableColumn colTPriceForMonthly;
    public ComboBox cmbYearForMonthlyIncome1;
    public ComboBox cmbMothForMonthlyIncome;
    public Label lblMonthlyIncome;
    public Label lblMostMovableItem;
    public TableView tblDailyDetails;
    public TableColumn colOIDForDaily;
    public TableColumn colCIDForDaily;
    public TableColumn colOTimeForDaily;
    public TableColumn colTDiscountForDaily;
    public TableColumn colTPriceForDaily;
    public TableView tblCustomerVise;
    public TableColumn colOIDOfCustomer;
    public TableColumn colCIDOfCustomer;
    public TableColumn colOTimeOfCustomer;
    public TableColumn colTDiscountOfCustomer;
    public TableColumn colTPriceOfCustomer;
    public ComboBox cmbCustomerIDS;
    public Label lblCustomerVice;
    public Label lblDailyIncome;
    public DatePicker datePicker;
    public Label lblLeastMovableItem;
    public TableView tblCustomerDetails;
    public TableColumn colCustomerID;
    public TableColumn colCustomerTitle;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerCity;
    public TableColumn colCustomerProvince;
    public TableColumn colCustomerPostalCode;
    public TableColumn colCustomerNationalID;
    public TableView tblItemDetails;
    public TableColumn colItemCode;
    public TableColumn colItemDescription;
    public TableColumn colItemPackSize;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQuantityInStore;
    public TableColumn colItemDiscount;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);


    public void initialize() {
        setItemCode();
    }

    public void addNewItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                lblItemCode.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQTYOnHand.getText()),
                txtDescription.getText(), Double.parseDouble(txtDiscount.getText())
        );

        if (new ItemDAOImpl().add(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            initialize();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    private void setItemCode() {
        try {
                                //getItemCode
            lblItemCode.setText(itemBO.generateNewID());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void selectItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String itemCode = txtModifyId.getText();

        Item i1 = itemBO.getItemCode(itemCode);
        lblModifyItemCode.setText(txtModifyId.getText());
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }
    }

    void setData(Item i) {
        txtModifyPackSize.setText(i.getPackSize());
        txtModifyUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtModifyQYTOnHand.setText(String.valueOf(i.getQtyOnHand()));
        txtModifyDescription.setText(i.getDescription());
        txtModifyDiscount.setText(String.valueOf(i.getDiscount()));

    }

    public void upDateItemDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item c1 = new Item(
                lblModifyItemCode.getText(), txtModifyPackSize.getText(), Double.parseDouble(txtModifyUnitPrice.getText()), Integer.parseInt(txtModifyQYTOnHand.getText()),
                txtModifyDescription.getText(), Double.parseDouble(txtModifyDiscount.getText())

        );

        if (new ItemDAOImpl().update(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();


    }

    public void deleteItemDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemDAOImpl().delete(txtDeleteId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void selectDeleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemCode = txtDeleteId.getText();

        Item i = new ItemDAOImpl().getItems(itemCode);
        lblDeleteItemCode.setText(txtDeleteId.getText());
        if (i == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData1(i);
        }
    }

    void setData1(Item c) {
        txtDeletePackSize.setText(c.getPackSize());
        txtDeleteUnitPrice.setText(String.valueOf(c.getUnitPrice()));
        txtDeleteQTYOnHand.setText(String.valueOf(c.getQtyOnHand()));
        txtDeleteDescription.setText(c.getDescription());
        txtDeleteDiscount.setText(String.valueOf(c.getDiscount()));
    }


    public void gotoDashBordForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdminDashBordForm.getChildren().clear();
        AdminDashBordForm.getChildren().add(load);
    }


}
