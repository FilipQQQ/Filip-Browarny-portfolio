package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Discount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindDiscountTest {

    @Test
    void findByID() {
        Discount dis = FindDiscount.findByID(1);
        assertEquals(dis.getID(), 1);
        assertEquals(dis.getValue(), 79);
        assertEquals(dis.getName(), "Inwalida (78%)");
    }

    @Test
    void findByName() {
        Discount dis = FindDiscount.findByName("Inwalida (78%)");
        assertEquals(dis.getID(), 1);
        assertEquals(dis.getValue(), 79);
        assertEquals(dis.getName(), "Inwalida (78%)");
    }
}