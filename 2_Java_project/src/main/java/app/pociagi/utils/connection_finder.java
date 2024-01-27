package app.pociagi.utils;

import app.pociagi.db.Utils.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;

public class connection_finder {

    public static ArrayList<ArrayList<Integer>> find(String begin, String end) {
        //funkcja zwraca format [1, 8, 39, 9, 10, 31] <- [id, godzina_wyruszenia, minuuta_wyruszenia
        //, godzina_dojazdu, minuta_dojazdu, czas_dojazdu_w_minutach
        DatabaseHandler handle = DatabaseHandler.getInstance();
        String sql_query = String.format("SELECT a.connection_id c, TO_CHAR(a.departure_hour, 'HH24') d, " +
                "TO_CHAR(a.departure_hour, 'MI') e, " +
                "TO_CHAR(b.arrival_hour, 'HH24') f, TO_CHAR(b.arrival_hour, 'MI') g" +
                " FROM (SELECT * FROM stops WHERE station_id" +
                " = (SELECT station_id from stations where name = '%s')) a " +
                "INNER JOIN (SELECT * FROM stops WHERE station_id = (SELECT station_id " +
                "from stations where name = '%s')) b ON a.connection_id = " +
                "b.connection_id WHERE a.departure_hour < b.arrival_hour", begin, end);
        ResultSet rs = handle.executeQuery(sql_query);
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        try {
            while (rs.next()) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                row.add(rs.getInt("c"));
                row.add(rs.getInt("d"));
                row.add(rs.getInt("e"));
                row.add(rs.getInt("f"));
                row.add(rs.getInt("g"));
                row.add(60*(rs.getInt("f")-rs.getInt("d"))+
                        rs.getInt("g")-rs.getInt("e"));
                arr.add(row);
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return arr;
    }

}
