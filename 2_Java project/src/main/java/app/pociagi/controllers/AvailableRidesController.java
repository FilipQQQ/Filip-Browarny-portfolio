package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Finders.Single.FindStop;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.Ticket;
import app.pociagi.db.Objects.User;
import app.pociagi.db.Utils.Route;
import app.pociagi.db.Utils.RouteFinder;
import app.pociagi.utils.connection_finder;
import app.pociagi.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AvailableRidesController implements Initializable {
    AppData appData = AppData.getInstance();
    ArrayList<ArrayList<Integer>> avail_cons;
    ArrayList<ArrayList<Route>> availRoutes;

    public AvailableRidesController() {}

    @FXML
    private ListView<String> availableRidesListView;

    private ArrayList<ArrayList<Connection>> possibleCons;
    @FXML
    private Label infoLabel, errorLabel, date;

    @FXML
    private Button goBackButton;

    public void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "main_menuv2.fxml");
    }

    public void buyTicketButtonPushed(ActionEvent e) {
        ArrayList<Connection> pckCons = possibleCons.get(
                availableRidesListView.getSelectionModel().getSelectedIndex());
        appData.pickedConnections = pckCons;
        appData.buyTicketData = new ArrayList<>();
        if (appData.user == null) {
            appData.user = new User(null, null, null, null, null);
        }
        for (Connection con : appData.pickedConnections) {
            appData.buyTicketData.add(new Ticket(null, con.getID(),
                    appData.pickedDate,
                    con.getDepartureStationId(),
                    con.getArrivalStationId(),
                    appData.user.getID(),
                    3,
                    null,
                    null,
                    0));
        }
        appData.currentTicketIndex = 0;
        SceneChanger.changeScene(e, "buy_ticket.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(dateFormat.format(appData.pickedDate));
        errorLabel.setText("");
        possibleCons = RouteFinder.FindBetween(appData.from.getName(), appData.destination.getName());
        for (ArrayList<Connection> con : possibleCons) {
            String str = "";
            for (Connection fragment : con) {
                str = str.concat(String.format("%s, %s --> %s, %s",
                        FindStation.findById(fragment.getDepartureStationId()).getName(),
                        FindStop.findByConIdStationId(
                                fragment.getID(),
                                fragment.getDepartureStationId()).getDepartureHour()
                        ,
                        FindStation.findById(fragment.getArrivalStationId()).getName(),
                        FindStop.findByConIdStationId(
                                fragment.getID(),
                                fragment.getArrivalStationId()).getArrivalHour()));
                str = str.concat("   ");
            }
            availableRidesListView.getItems().add(str);
        }

    }
}
