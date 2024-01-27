package app.pociagi.db.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * should be rewritten!
 */
public class DatabaseHandler {

    private static DatabaseHandler handler = null;
    private static String dbURL;
    private static String username;
    private static String password;
    public static Connection con = null;
    public static Statement stmt = null;

    private DatabaseHandler() {
        getPassword();
        createConnection();
    }

    public static DatabaseHandler getInstance() {
        if (handler==null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }
    private void getPassword() {
                // pass the path to the file as a parameter
        File file = new File("pass.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        dbURL = sc.nextLine();
        username = sc.nextLine();
        password = sc.nextLine();
    }

    void createConnection() {
        try {
            con = DriverManager.getConnection(dbURL, username, password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void executeUpdate(String sql) {
        try {
            int rows = stmt.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("Update executed successfully!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    public void insertData(String sql) {
        try {
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void printData(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println("Id " + rs.getString(1) + ", " + rs.getString(2));
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    public ArrayList<String> returnDataArray(ResultSet rs, int columnIndex) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            while (rs.next()) {
                arr.add(rs.getString(columnIndex));
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return arr;
    }

    public ArrayList<HashMap<String, String>> returnAllData(ResultSet rs) {
        ArrayList<HashMap<String, String>> array = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            Integer columnCount = resultSetMetaData.getColumnCount();
            while (rs.next()) {
                HashMap<String, String> data = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    data.put(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                array.add(data);
            }
        }
        catch (SQLException e) {
            return null;
        }
        catch (NullPointerException e) {
            return null;
        }
        return array;
    }

    public void finish() {
        try {
            stmt.close();
            con.close();
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
