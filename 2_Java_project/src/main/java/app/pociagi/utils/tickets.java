package app.pociagi.utils;

import app.pociagi.db.Utils.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class tickets {
    public static ArrayList<String> ticket_list(int user_id) {
        DatabaseHandler handle = DatabaseHandler.getInstance();
        String sql_query = String.format("select t.ticket_id a, n.name b, TO_CHAR(s.departure_hour, 'HH24')" +
                " c, TO_CHAR(s.departure_hour, 'MI') d, nn.name e, TO_CHAR(st.arrival_hour, 'HH24') " +
                "f, TO_CHAR(st.arrival_hour, 'MI') g from tickets t join rides r on (r.ride_id = t.ride_id)" +
                " join stations n on (n.station_id = t.id_departure_station) join stations nn on " +
                "(nn.station_id = t.id_arrival_station) right join stops s on (s.connection_id = r." +
                "connection_id) right join stops st on (st.connection_id = r.connection_id) where " +
                "t.user_id = %s and " +
                "t.id_departure_station = s.station_id and t.id_arrival_station = st.station_id", user_id);
        ResultSet rs = handle.executeQuery(sql_query);
        ArrayList<String> arr = new ArrayList<String>();
        try {
            while (rs.next()) {
                String row = new String();
                //row =rs.getString("a") + " ";//ticket_id nam nie potrzebne do wyswietlenia
                row +=rs.getString("b") + " ";
                row +=rs.getString("c") + ":";
                row +=rs.getString("d") + " --> ";
                row +=rs.getString("e") + " ";
                row +=rs.getString("f") + ":";
                row +=rs.getString("g");
                arr.add(row);
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return arr;
    }
    public static void buy_ticket (int ride_id, int begin_station_id, int end_station_id,
                                   String user_id, String name, String surname) {
        String sql_query;
        if ((user_id == null && (name == null || surname == null)) ||
        user_id != null && (name != null || surname != null) ||
                (name == null && surname != null) || (name != null && surname == null)) {
            throw new java.lang.Error("Wrong arguments!");
        }
        else if (user_id == null) {
            sql_query = String.format("INSERT INTO tickets VALUES (default, %s, %s, %s, Null, '%s', '%s')",
                    ride_id, begin_station_id, end_station_id, name, surname);
        }
        else {
            sql_query = String.format(
                    "INSERT INTO tickets VALUES (default, %s, %s, %s, %s, Null, Null)",
                    ride_id, begin_station_id, end_station_id, user_id);
        }
        //sql_query = "INSERT INTO tickets VALUES (default, 1, 6, 9, 18, Null, Null);";
        DatabaseHandler handle = DatabaseHandler.getInstance();
        handle.insertData(sql_query);
    }
}
