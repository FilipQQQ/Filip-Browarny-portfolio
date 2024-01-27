package app.pociagi.db.Finders.All;

import app.pociagi.db.Objects.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AllFindUser {
    public static ArrayList<User> findByUserID(Integer userID) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("USERS", "USER_ID", userID);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    public static ArrayList<User> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("USERS");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL STATE: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<User> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<User> usersList = new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            usersList.add(new User(Integer.parseInt(conData.get("USER_ID")), conData.get("LOGIN"), conData.get("PASSWORD"), conData.get("NAME"), conData.get("SURNAME")));
        }
        return usersList;
    }
}
