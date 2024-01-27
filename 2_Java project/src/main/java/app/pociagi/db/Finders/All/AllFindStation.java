package app.pociagi.db.Finders.All;

import app.pociagi.db.Objects.Station;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AllFindStation {
    public static ArrayList<Station> findByStationID(Integer stationId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("STATIONS", "STATION_ID", stationId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    public static ArrayList<Station> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("STATIONS");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<Station> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<Station> stationsList = new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            System.out.println(conData);
            stationsList.add(new Station(
                    Integer.parseInt(conData.get("STATION_ID")),
                    conData.get("NAME"),
                    Integer.parseInt(conData.get("CONNECTION_STATION"))
            ));
        }
        return stationsList;
    }
}
