package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Connection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindConnectionTest {

    @Test
    void findByID() {
        Connection con = FindConnection.findByID(1);
        assertEquals(con.getID(), 1);
        assertEquals(con.getDepartureStationId(), 1);
        assertEquals(con.getArrivalStationId(), 24);
    }
}