package app.pociagi.controllers;

import app.pociagi.SceneChanger;
import app.pociagi.db.Finders.All.AllFindTicket;
import app.pociagi.db.Objects.Seat;
import app.pociagi.db.Objects.Ticket;
import app.pociagi.utils.AppData;
import app.pociagi.db.Utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PickSeatController implements Initializable {

    @FXML
    private Button goBackButton;
    private AppData appData = AppData.getInstance();
    private DatabaseHandler handler = DatabaseHandler.getInstance();

    public ListView<String> seatList;

    private Ticket ticketData;
    public Button confirmButton;
    private ArrayList<Seat> availSeats = new ArrayList<>();
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Seat> allSeats = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 50; j++) {
                allSeats.add(new Seat(null, null, i, j));
            }
        }
        ticketData = appData.buyTicketData.get(appData.currentTicketIndex);
        ArrayList<Ticket> ticketList = AllFindTicket.findByConIDdate(
                ticketData.getConnectionId(), ticketData.getDate()
        );
        for (Ticket t : ticketList) {
            System.out.println(String.format("%d, %d", t.getCarId(), t.getSeatId()));
        }
        for (Seat seat : allSeats) {
            Boolean isOccupied = false;
            for (Ticket ticket : ticketList) {
                if (seat.getCarNumber() == ticket.getCarId() &&
                seat.getSeatNumber() == ticket.getSeatId()) {
                    isOccupied = true;
                }
            }
            if (!isOccupied) {
                availSeats.add(seat);
            }
        }
        for (Seat seat : availSeats) {
            seatList.getItems().add(String.format("Car: %d, Seat: %d",
                    seat.getCarNumber(), seat.getSeatNumber()));
        }
    }

    public void goBackPressed(ActionEvent e) {
        SceneChanger.changeScene(e,"buy_ticket.fxml");
    }

    public void pickPlacePressed(ActionEvent e) {
        Seat pickedSeat = availSeats.get(seatList.getSelectionModel().getSelectedIndex());
        ticketData.setSeatId(pickedSeat.getSeatNumber());
        ticketData.setCarId(pickedSeat.getCarNumber());
        appData.buyTicketData.set(appData.currentTicketIndex, ticketData);
        SceneChanger.changeScene(e, "buy_ticket.fxml");
    }
}
