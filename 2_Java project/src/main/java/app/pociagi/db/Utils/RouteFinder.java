package app.pociagi.db.Utils;

import app.pociagi.db.Finders.All.AllFindStop;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.Station;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RouteFinder {
    public static ArrayList<ArrayList<Connection>> FindBetween(String departure, String destination) {
        DatabaseHandler handle = DatabaseHandler.getInstance();
        String sql_query = String.format("select stops.connection_id a, stops.station_id b, stop2.station_id c " +
                "from stops cross join stops stop2 where stops.connection_id = stop2.connection_id and " +
                "stops.station_id = (select station_id from stations where name = '%s') " +
                "and stop2.station_id = (select station_id from stations where name = " +
                "'%s') and extract(hour from stop2.arrival_hour)*60 + " +
                "extract(minute from stop2.arrival_hour) - extract(hour from stops.departure_hour)*60 - " +
                "extract(minute from stops.departure_hour) > 0", departure, destination);
        ResultSet rs = handle.executeQuery(sql_query);
        ArrayList<ArrayList<Connection>> availRoutes = new ArrayList<>();
        ArrayList<Connection> inside = new ArrayList<>();
        try {
            while (rs.next()) {
                inside.add(new Connection(rs.getInt("a"), rs.getInt("b"), rs.getInt("c")));
                availRoutes.add(inside);
                inside = new ArrayList<>();
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        // jesli by sie okazalo ze znalezlismy przejazd bezposredni nie szukamy przesiadek
        if (!availRoutes.isEmpty())
            return availRoutes;


        sql_query = String.format("select stops.connection_id a, stop2.station_id b, stops.station_id c, stop3.connection_id d, stop3.station_id e, stop4.station_id f\n " +
                "from stops join stations st on stops.station_id = st.station_id cross join stops stop2 cross join stops stop3 cross join stops stop4\n " +
                "where stops.connection_id = stop2.connection_id and stop2.station_id = (select station_id from stations where name = '%s') and\n " +
                "stops.connection_id in (select stops.connection_id from stops join stations st on stops.station_id = st.station_id where st.name = '%s') and \n" +
                "st.connection_station = 1 and\n " +
                "extract(hour from stop2.departure_hour)*60 + extract(minute from stop2.departure_hour) - extract(hour from stops.arrival_hour)*60 - extract(minute from stops.arrival_hour) < 0 and " +
                "extract(hour from stop3.departure_hour)*60 + extract(minute from stop3.departure_hour) - extract(hour from stop4.arrival_hour)*60 - extract(minute from stop4.arrival_hour) < 0 and " +
                "extract(hour from stops.arrival_hour)*60 + extract(minute from stops.arrival_hour) - extract(hour from stop3.departure_hour)*60 - extract(minute from stop3.departure_hour) < 0 and\n " +
                "stops.station_id = stop3.station_id and \n" +
                "stop3.connection_id = stop4.connection_id and \n" +
                "stop4.station_id = (select station_id from stations where name = '%s') and\n " +
                "extract(hour from stop4.arrival_hour)*60 + extract(minute from stop4.arrival_hour) - extract(hour from stop2.departure_hour)*60 - extract(minute from stop2.departure_hour) < 90 + \n" +
                "(select min(extract(hour from stop4.arrival_hour)*60 + extract(minute from stop4.arrival_hour) - extract(hour from stop2.departure_hour)*60 - extract(minute from stop2.departure_hour))\n " +
                "from stops join stations st on stops.station_id = st.station_id cross join stops stop2 cross join stops stop3 cross join stops stop4\n " +
                "where stops.connection_id = stop2.connection_id and \n " +
                "stop2.station_id = (select station_id from stations where name = '%s') and\n " +
                "extract(hour from stop2.departure_hour)*60 + extract(minute from stop2.departure_hour) - extract(hour from stops.arrival_hour)*60 - extract(minute from stops.arrival_hour) < 0 and " +
                "extract(hour from stop3.departure_hour)*60 + extract(minute from stop3.departure_hour) - extract(hour from stop4.arrival_hour)*60 - extract(minute from stop4.arrival_hour) < 0 and " +
                "extract(hour from stops.arrival_hour)*60 + extract(minute from stops.arrival_hour) - extract(hour from stop3.departure_hour)*60 - extract(minute from stop3.departure_hour) < 0 and\n " +
                "stops.connection_id in \n" +
                "(select stops.connection_id from stops join stations st on stops.station_id = st.station_id where st.name = '%s') \n" +
                "and st.connection_station = 1 and TO_CHAR(stops.departure_hour,'HH24:MI') >= TO_CHAR(stop2.arrival_hour,'HH24:MI') and stops.station_id = stop3.station_id\n " +
                "and stop3.connection_id = stop4.connection_id and stop4.station_id = (select station_id from stations where name = '%s'))", departure, departure, destination, departure, departure, destination);
        rs = handle.executeQuery(sql_query);
        try {
            while (rs.next()) {
                inside.add(new Connection(rs.getInt("a"), rs.getInt("b"), rs.getInt("c")));
                inside.add(new Connection(rs.getInt("d"), rs.getInt("e"), rs.getInt("f")));
                availRoutes.add(inside);
                inside = new ArrayList<>();
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return availRoutes;
    }
}