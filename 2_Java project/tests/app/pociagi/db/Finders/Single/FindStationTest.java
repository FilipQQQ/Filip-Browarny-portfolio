package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindStationTest {

    @Test
    void findByName() {
        Station st = FindStation.findByName("Wrocław Główny");
        assertEquals(st.getID(), 1);
        assertEquals(st.getName(), "Wrocław Główny");
        assertEquals(st.getConnectionStation(), 1);
    }

    @Test
    void findById() {
        Station st = FindStation.findById(1);
        assertEquals(st.getID(), 1);
        assertEquals(st.getName(), "Wrocław Główny");
        assertEquals(st.getConnectionStation(), 1);
    }
}