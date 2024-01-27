package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.SeatClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindSeatClassTest {

    @Test
    void findByID() {
        SeatClass st = FindSeatClass.findByID(1);
        assertEquals(st.getID(), 1);
        assertEquals(st.getName(), "Klasa 1");
        assertEquals(st.getCoef(), 2);
    }

    @Test
    void findByName() {
        SeatClass st = FindSeatClass.findByName("Klasa 1");
        assertEquals(st.getID(), 1);
        assertEquals(st.getName(), "Klasa 1");
        assertEquals(st.getCoef(), 2);
    }
}