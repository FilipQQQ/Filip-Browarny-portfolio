package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.All.AllFindConnection;
import app.pociagi.db.Finders.All.AllFindStation;
import app.pociagi.db.Finders.All.AllFindStop;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Finders.Single.FindStop;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.DBObject;
import app.pociagi.db.Objects.Station;
import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.utils.AppData;
import app.pociagi.utils.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class AddNewConnectionPanel implements Initializable {

    @FXML
    private Button addConnectionButton, addStopButton, goBackButton;
    @FXML
    private ListView allStationsListView;

    @FXML
    AutoCompleteTextField new_station_name;

    @FXML
    private Label succesLabel;

    @FXML
    private TextField connectionIDTextField,
            fromTextField,
            toTextField,
            timeArrivalTextField,
            timeDepartureTextField,
            conIDfromStopsTextField,
            idTextField;

    public static ArrayList<Station> stationsList;
    private ArrayList<ConnectionStop> toRemove = new ArrayList<ConnectionStop>();
    private ArrayList<ConnectionStop> toAdd = new ArrayList<ConnectionStop>();
    private ArrayList<ConnectionStop> stops = new ArrayList<ConnectionStop>();
    private ArrayList<ConnectionStop> reversedStops = new ArrayList<ConnectionStop>();


    @FXML
    private void goBackButtonPushed(ActionEvent e) {
        SceneChanger.changeScene(e, "admin_panel.fxml");
    }

    @FXML
    public void addConnectionButtonPushed(ActionEvent e) {
        if (AppData.getInstance().connection.getID() != null) {
            editClicked();
            return;
        }
        try {
            AppData.getInstance().connection.setID(Integer.parseInt(idTextField.getText().toString()));
            AppData.getInstance().connection.setDepartureStationID(stops.get(0).getStationId());
            AppData.getInstance().connection.setArrivalStationId(stops.get(stops.size() - 1).getStationId());
            Connection reversedConnection = new Connection(AppData.getInstance().connection.getID()+1, AppData.getInstance().connection.getArrivalStationId(), AppData.getInstance().connection.getDepartureStationId());

            AppData.getInstance().connection.pushToDB();
            //reversedConnection.pushToDB();

            //ArrayList<Integer> stopsIdsReversed = new ArrayList<>();

//            for(ConnectionStop stop : stops) {
//                stopsIdsReversed.add(stop.getStationId());
//            }
            //Collections.reverse(stopsIdsReversed);
            int i = 0;
            for (ConnectionStop stop : stops) {
                stop.setConnectionID(AppData.getInstance().connection.getID());
                //ConnectionStop sReversed = new ConnectionStop(reversedConnection.getID(), stopsIdsReversed.get(i), stop.getArrivalHour(), stop.getDepartureHour());
                stop.pushToDB();
//                sReversed.pushToDB();
//                i++;
            }
            succesLabel.setText("SUCCESS");
            succesLabel.setVisible(true);
            SceneChanger.changeScene(e, "admin_panel.fxml");
        } catch (Exception ex) {
            succesLabel.setText("FAILURE");
            succesLabel.setVisible(true);
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void addStopButtonPushed(ActionEvent e) throws ParseException {
        LocalTime timeArrival = LocalTime.parse(timeArrivalTextField.getText().toString());
        LocalTime timeDeparture = LocalTime.parse(timeDepartureTextField.getText().toString());
        Time tArr = Time.valueOf(timeArrival);
        Time tDep = Time.valueOf(timeDeparture);
        ConnectionStop stop = new ConnectionStop(AppData.getInstance().connection.getID(), FindStation.findByName(new_station_name.getText().toString()).getID(), tArr, tDep);
        stops.add(stop);
        allStationsListView.getItems().add(stop.toString());
        toAdd.add(stop);
        new_station_name.setText("");
        timeArrivalTextField.setText("");
        timeDepartureTextField.setText("");
        sortStops();
        allStationsListView.getItems().clear();
        for (ConnectionStop stopa : stops) {
            allStationsListView.getItems().add(stopa.toString());
        }
    }

    @FXML
    public void deleteStopButtonPushed(ActionEvent e) throws ParseException {
        toRemove.add(stops.get(allStationsListView.getSelectionModel().getSelectedIndex()));
        stops.remove(allStationsListView.getSelectionModel().getSelectedIndex());
        allStationsListView.getItems().remove(allStationsListView.getSelectionModel().getSelectedIndex());
        sortStops();
        allStationsListView.getItems().clear();
        for (ConnectionStop stop : stops) {
            allStationsListView.getItems().add(stop.toString());
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        if (AppData.getInstance().connection.getID() != null) {
            stops.addAll(AllFindStop.findByConnectionID(
                    AppData.getInstance().connection.getID()
            ));
            sortStops();
            allStationsListView.getItems().clear();
            for (ConnectionStop stop : stops) {
                allStationsListView.getItems().add(stop.toString());
            }
            addConnectionButton.setText("Update");
        }
        succesLabel.setVisible(false);
        ArrayList<Station> list = AllFindStation.getAll();
        for (Station s : list) {
            new_station_name.getEntries().add(s.getName());
        }
    }

    public void editClicked() {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        for (ConnectionStop stop : toRemove) {
            String sql = String.format("DELETE FROM STOPS WHERE CONNECTION_ID=%s AND STATION_ID=%s",
                    stop.getConnectionId(),
                    stop.getStationId());
            handler.executeQuery(sql);
        }
        for (ConnectionStop stop : toAdd) {
            stop.pushToDB();
        }
        ConnectionStop minStop = stops.get(0);
        ConnectionStop maxStop = stops.get(stops.size()-1);
        for (ConnectionStop stop : stops) {
            if (stop.getDepartureHour().before(minStop.getDepartureHour())) {
                minStop = stop;
            }
            if (stop.getArrivalHour().after(maxStop.getArrivalHour())) {
                maxStop = stop;
            }
        }
        String sql = String.format("UPDATE CONNECTIONS SET DEPARTURE_STATION=%s, ARRIVAL_STATION=%s WHERE " +
                        "CONNECTION_ID=%s",
                minStop.getStationId(),
                maxStop.getStationId(),
                AppData.getInstance().connection.getID());
        handler.executeQuery(sql);
        toRemove.clear();
        toAdd.clear();
        succesLabel.setVisible(true);
    }

    public void sortStops() {
        Collections.sort(stops, new Comparator<ConnectionStop>() {
            @Override
            public int compare(ConnectionStop o1, ConnectionStop o2) {
                if (o1.getDepartureHour().before(o2.getDepartureHour())) {
                    return -1;
                }
                if (o1.getDepartureHour().equals(o2.getDepartureHour())) {
                    return 0;
                }
                if (o1.getDepartureHour().after(o2.getDepartureHour())) {
                    return 1;
                }
                return 0;
            }
        });
    }
}