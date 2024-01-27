package app.pociagi.db.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void setEmail() {
        User usr = new User(1, "kkuba", "kuba", "Jakub", "Kowalski");
        usr.setEmail("a@gmail.com");
        assertEquals("a@gmail.com", usr.getEmail());
    }

    @Test
    void getSurname() {
        User usr = new User(1, "kkuba", "kuba", "Jakub", "Kowalski");
        assertEquals("Kowalski", usr.getSurname());
    }


    @Test
    void getLogin() {
        User usr = new User(1, "kkuba", "kuba", "Jakub", "Kowalski");
        assertEquals("kkuba", usr.getLogin());
    }

    @Test
    void getName() {
        User usr = new User(1, "kkuba", "kuba", "Jakub", "Kowalski");
        assertEquals("Jakub", usr.getName());
    }

    @Test
    void getPassword() {
        User usr = new User(1, "kkuba", "kuba", "Jakub", "Kowalski");
        assertEquals("kuba", usr.getPassword());
    }
}