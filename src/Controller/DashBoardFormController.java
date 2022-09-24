package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public AnchorPane DashBoardForm;


    public void gotoCashierLoginForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        DashBoardForm.getChildren().clear();
        DashBoardForm.getChildren().add(load);
    }


    public void gotoAdminLoginForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        DashBoardForm.getChildren().clear();
        DashBoardForm.getChildren().add(load);
    }
}
