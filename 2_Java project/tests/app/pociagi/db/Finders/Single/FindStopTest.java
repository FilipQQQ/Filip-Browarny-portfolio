package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.ConnectionStop;
import org.junit.jupiter.api.Test;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class FindStopTest {

    @Test
    void findByConIdStationId() {
        ConnectionStop cs = FindStop.findByConIdStationId(1, 4);
        assertEquals(cs.getStationId(), 4);
        assertEquals(cs.getDepartureHour(), new Time(6, 48, 0));
        assertEquals(cs.getConnectionId(), 1);
        assertEquals(cs.getArrivalHour(), new Time(6, 47, 0));
    }
}