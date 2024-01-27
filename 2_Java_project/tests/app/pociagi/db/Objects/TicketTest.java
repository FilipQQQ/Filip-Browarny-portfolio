package app.pociagi.db.Objects;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {




    @Test
    void setDiscountId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        t.setDiscountId(3);
        assertEquals(3, t.getDiscountId());
    }



    @Test
    void setUserId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        t.setUserId(3);
        assertEquals(3, t.getUserId());
    }

    @Test
    void getArrivalStationId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        t.setUserId(3);
        assertEquals(3, t.getUserId());
    }

    @Test
    void getDepartureStationId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(2, t.getDepartureStationId());
    }

    @Test
    void getConnectionId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(2, t.getConnectionId());
    }

    @Test
    void getUserId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(2, t.getUserId());
    }


    @Test
    void getCarId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(1, t.getCarId());
    }

    @Test
    void getDiscountId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(3, t.getDiscountId());
    }

    @Test
    void getSeatId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(2, t.getSeatId());
    }

    @Test
    void setPrice() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        t.setPrice(30);
        assertEquals(30, t.getPrice());
    }

    @Test
    void getPrice() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        assertEquals(40, t.getPrice());
    }

    @Test
    void setCarId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        t.setCarId(4);
        assertEquals(4, t.getCarId());
    }

    @Test
    void setSeatId() {
        Ticket t = new Ticket(1, 2, new Date(), 2, 1, 2, 3, 1, 2, 40);
        t.setSeatId(56);
        assertEquals(56, t.getSeatId());
    }
}