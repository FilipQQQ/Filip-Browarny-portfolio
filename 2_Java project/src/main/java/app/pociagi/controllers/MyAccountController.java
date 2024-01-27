package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.All.AllFindTicket;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Finders.Single.FindStop;
import app.pociagi.db.Objects.Ticket;
import app.pociagi.utils.tickets;
import app.pociagi.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MyAccountController implements Initializable {
    AppData appdata = AppData.getInstance();
    @FXML
    private Label nameLabel, surnameLabel, emailLabel, loginLabel;

    @FXML
    private ListView myRidesListView;

    public void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText("Name: " + appdata.user.getName());
        surnameLabel.setText("Surname: " + appdata.user.getSurname());
        loginLabel.setText("Login: " + appdata.user.getLogin());
        emailLabel.setText("Email: " + appdata.user.getEmail());

        ArrayList<Ticket> ticket_list = AllFindTicket.findByUserID(appdata.user.getID());
        if (ticket_list != null) {
            for (var s : ticket_list) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = s.getDate();
                myRidesListView.getItems().add(String.format("%s %s %s --> %s %s  Car: %d, Seat: %d",
                        dateFormat.format(date),
                        FindStation.findById(s.getDepartureStationId()).getName(),
                        FindStop.findByConIdStationId(s.getConnectionId(), s.getDepartureStationId()).getDepartureHour(),
                        FindStation.findById(s.getArrivalStationId()).getName(),
                        FindStop.findByConIdStationId(s.getConnectionId(), s.getArrivalStationId()).getArrivalHour(),
                        s.getCarId(), s.getSeatId()
                ));
            }
        }

    }
}
