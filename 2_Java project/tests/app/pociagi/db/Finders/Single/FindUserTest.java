package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUserTest {

    @Test
    void findByLogin() {
        User us = FindUser.findByLogin("admin");
        assertEquals(us.getLogin(), "admin");
        assertEquals(us.getName(), "admin");
        assertEquals(us.getID(), 183);
        assertEquals(us.getEmail(), "admin");
        assertEquals(us.getSurname(), "admin");
    }

    @Test
    void findByID() {
        User us = FindUser.findByID(183);
        assertEquals(us.getLogin(), "admin");
        assertEquals(us.getName(), "admin");
        assertEquals(us.getID(), 183);
        assertEquals(us.getEmail(), "admin");
        assertEquals(us.getSurname(), "admin");
    }
}