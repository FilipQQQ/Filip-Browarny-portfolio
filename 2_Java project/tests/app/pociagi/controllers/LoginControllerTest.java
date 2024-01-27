package app.pociagi.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void checkLogin() throws Exception {
        assertEquals(true, LoginController.checkLogin("admin", "admin"));
    }

    @Test
    void checkLoginEmptyUsr() throws Exception {
        Throwable exception = assertThrows(Exception.class, () -> LoginController.checkLogin("", "passwd"));
        assertEquals("You need to enter password and login!", exception.getMessage());
    }

    @Test
    void checkLogniEmptyPasswd() throws Exception {
        Throwable exception = assertThrows(Exception.class, () -> LoginController.checkLogin("admin", ""));
        assertEquals("Wrong password!", exception.getMessage());
    }

    @Test
    void checkLoginWrongPasswd() throws Exception {
        Throwable exception = assertThrows(Exception.class, () -> LoginController.checkLogin("admin", "ad"));
        assertEquals("Wrong password!", exception.getMessage());
    }

    @Test
    void checkLoginNoSuchUsr() throws Exception {
        Throwable exception = assertThrows(Exception.class, () -> LoginController.checkLogin("admin1", "ad"));
        assertEquals("There is no such user!", exception.getMessage());
    }
}