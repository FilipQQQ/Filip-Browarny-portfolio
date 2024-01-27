package app.pociagi.controllers;

import app.pociagi.db.Finders.All.AllFindDiscount;
import app.pociagi.db.Finders.Single.*;
import app.pociagi.db.Objects.*;
import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.SceneChanger;
import app.pociagi.utils.tickets;
import app.pociagi.utils.AppData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BuyTicketController implements Initializable {
    private AppData appData = AppData.getInstance();
    private DatabaseHandler handler = DatabaseHandler.getInstance();
    private Integer rideId;
    private Integer cost;
    private ObservableList<String> discountNames;

    Boolean checkoutLocked = true;

    @FXML
    private Button checkoutButton;
    @FXML
    private ChoiceBox<String> discountList;

    private ObservableList<String> classNames;

    @FXML
    private ListView<String> classPicker;

    @FXML
    private ListView<String> ticketList;

    private ArrayList<Discount> discounts = AllFindDiscount.getAll();

    private Ticket ticketData;
    private Connection conData;
    private ConnectionStop depStop, arrStop;
    private Station departureStation, arrivalStation;
    @FXML
    private Label price, fromTime, toTime, fromLabel, toLabel, seatPicked, date;
    @FXML
    private Button goBack;

    @FXML
    private Label errorLabel, sumPrice;
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(dateFormat.format(appData.pickedDate));
        Integer index = 1;
        for (Connection conData : AppData.getInstance().pickedConnections) {
            //System.out.println(String.format("%d, %d, %d", conData.getID(), conData.getDepartureStationId(), conData.getArrivalStationId()));
            ticketList.getItems().add(String.format("#%d: %s (%s) - %s (%s)",
                    index,
                    FindStation.findById(conData.getDepartureStationId()).getName(),
                    FindStop.findByConIdStationId(conData.getID(), conData.getDepartureStationId()).getDepartureHour().toString(),
                    FindStation.findById(conData.getArrivalStationId()).getName(),
                    FindStop.findByConIdStationId(conData.getID(), conData.getArrivalStationId()).getArrivalHour().toString()
                    ));
            index++;
        }

        errorLabel.setText("");

        ticketList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ticketChange();
            }
        });

        for (Discount disc : discounts) {
            discountList.getItems().add(disc.getName());
        }
        discountList.getItems().addAll();

        discountList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Discount discount = FindDiscount.findByName(discountList.getSelectionModel().getSelectedItem());
                ticketData.setDiscountId(discount.getID());
                updateTicket();
                recalculateCost(depStop.getDepartureHour(), arrStop.getArrivalHour(), discount.getValue(), 1);
            }
        });

        ticketList.getSelectionModel().select(appData.currentTicketIndex);

    }
    private void ticketChange() {
        ticketData = appData.buyTicketData.get(ticketList.getSelectionModel().getSelectedIndex());
        conData = appData.pickedConnections.get(ticketList.getSelectionModel().getSelectedIndex());
        depStop = FindStop.findByConIdStationId(
                conData.getID(),
                conData.getDepartureStationId()
        );
        arrStop = FindStop.findByConIdStationId(
                conData.getID(),
                conData.getArrivalStationId()
        );
        departureStation = FindStation.findById(conData.getDepartureStationId());
        arrivalStation = FindStation.findById(conData.getArrivalStationId());
        fromLabel.setText(departureStation.getName());
        toLabel.setText(arrivalStation.getName());
        fromTime.setText(depStop.getDepartureHour().toString());
        toTime.setText(arrStop.getArrivalHour().toString());
        seatPicked.setText(String.format("Car: %d, Seat: %d", ticketData.getCarId(), ticketData.getSeatId()));
        discountList.getSelectionModel().select(ticketData.getDiscountId());
        Discount discount = FindDiscount.findByName(discountList.getSelectionModel().getSelectedItem());
        recalculateCost(depStop.getDepartureHour(), arrStop.getArrivalHour(), discount.getValue(), 1);
    }

    public void recalculateCost(Time depHour, Time arrHour, Integer discountValue, Integer classCoef) {
        Integer time = (int) (arrHour.getTime() - depHour.getTime()) / 60000;
        //System.out.println(time);
        //System.out.println(discountValue);
        int cost = time * 26 * (100 - discountValue) / 100 * classCoef;
        price.setText(String.format("%d,%02d zł", cost/100, cost%100));
        ticketData.setPrice(cost);
        Integer sum = 0;
        for (Ticket ticket : appData.buyTicketData) {
            sum += ticket.getPrice();
        }
        sumPrice.setText(String.format("%d,%02d zł", sum/100, sum%100));
        updateTicket();
    }

    public void goBackPressed(ActionEvent e) {
        appData.buyTicketData = new ArrayList<>();
        SceneChanger.changeScene(e, "available_rides.fxml");
    }

    public void pickSeatPressed(ActionEvent e) {
        appData.currentTicketIndex = ticketList.getSelectionModel().getSelectedIndex();
        SceneChanger.changeScene(e, "pick_seat.fxml");
    }

    public void checkoutPressed(ActionEvent e) {
        if (!checkoutLocked) {
            //System.out.println("proceeded to checkout");
            for (Ticket ticket : appData.buyTicketData) {
                ticket.setUserId(appData.user.getID());
                ticket.pushToDB();
            }
            SceneChanger.changeScene(e, "main_menuv2.fxml");
        }
    }

    public void confirmPressed(ActionEvent e) {
        if (ticketData.getCarId() == null) {
            errorLabel.setText("SEAT NOT CHOSEN!");
        }
        else {
            errorLabel.setText("");
            ticketList.getSelectionModel().selectNext();
        }
        checkoutButton.setStyle("-fx-background-color: #4bfd00; ");
        checkoutLocked = false;
        for (Ticket ticket : appData.buyTicketData) {
            if (ticketData.getCarId() == null) {
                checkoutButton.setStyle("-fx-background-color: #ff0000; ");
                checkoutLocked = true;
            }
        }
    }

    private void updateTicket() {
        appData.buyTicketData.set(ticketList.getSelectionModel().getSelectedIndex(), ticketData);
    }

}
