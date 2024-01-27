package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.utils.AppData;
import app.pociagi.utils.AutoCompleteTextField;
import app.pociagi.db.Utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
//import javafx.scene.control.Label;

public class MenuController implements Initializable {
    AppData appdata = AppData.getInstance();


    @FXML
    private Button logInButton, findConnectionButton, myAccountButton, adminPanelButton;

    @FXML
    public AutoCompleteTextField fromWhereTextField, toWhereTextField;

    @FXML
    private Label helloLabel;

    @FXML
    private DatePicker datePicker;



    @FXML
    public void logInButtonPushed(ActionEvent event) {
            if(appdata.user != null) {
                logInButton.setText("LOG IN");
                appdata.user = null;
                SceneChanger.changeScene(event, "login_menu.fxml");
            } else {
                SceneChanger.changeScene(event, "login_menu.fxml");
            }
    }
    @FXML
    public void findConnectionButtonPushed(ActionEvent event)
    {
        LocalDate localDate = datePicker.getValue();
        if (localDate.compareTo(LocalDate.now()) >= 0) {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            appdata.pickedDate = Date.from(instant);
            //System.out.println(appdata.pickedDate);
            appdata.from = FindStation.findByName(fromWhereTextField.getText());
            appdata.destination = FindStation.findByName(toWhereTextField.getText());
            SceneChanger.changeScene(event,"available_rides.fxml" );
        }
        else {
            helloLabel.setText("cant buy ticket in the past");
        }

    }

    public void myAccountButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "my_account_view.fxml");
    }


    public void adminPanelButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = "SELECT * FROM STATIONS";
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 2);
        AutoCompleteTextField field = new AutoCompleteTextField();
        fromWhereTextField.getEntries().addAll(arr);
        toWhereTextField.getEntries().addAll(arr);
        adminPanelButton.setVisible(false);
        if(appdata.user == null) {
            helloLabel.setText("Hello, unknown!");
            logInButton.setText("LOG IN");
            myAccountButton.setVisible(false);
        } else if (appdata.user.getLogin().toString().equals("admin")) {
            logInButton.setText("LOG OUT");
            adminPanelButton.setVisible(true);
        } else {
            helloLabel.setText(String.format("Hello, %s", appdata.user.getName()));
            logInButton.setText("LOG OUT");
            myAccountButton.setVisible(true);
        }
    }
}