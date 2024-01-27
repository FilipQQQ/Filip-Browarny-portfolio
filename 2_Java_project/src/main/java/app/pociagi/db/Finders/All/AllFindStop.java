package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.ConnectionStop;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/** <h> AllFindStop </h>
 * AllFindStop allows to find ALL ConnectionStop objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindStop {

    /**
     * <h> find ALL by connection ID </h>
     * Finds all objects in STOPS table by id of connection
     * @param connectionId id of connection
     * @return ArrayList of ConnectionStop objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<ConnectionStop> findByConnectionID(Integer connectionId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("STOPS", "CONNECTION_ID", connectionId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by station ID </h>
     * Finds all objects in STOPS table by id of station
     * @param stationId id of station
     * @return ArrayList of ConnectionStop objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<ConnectionStop> findByStationID(Integer stationId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("STOPS", "STATION_ID", stationId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<ConnectionStop> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<ConnectionStop> stopList= new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            String arrivalHour = conData.get("ARRIVAL_HOUR").substring(11);
            //System.out.println(arrivalHour);
            String departureHour = conData.get("DEPARTURE_HOUR").substring(11);
            //System.out.println(departureHour);
            stopList.add(new ConnectionStop(
                    Integer.parseInt(conData.get("CONNECTION_ID")),
                    Integer.parseInt(conData.get("STATION_ID")),
                    new Time(Integer.parseInt(arrivalHour.substring(0,2)),
                            Integer.parseInt(arrivalHour.substring(3,5)), Integer.parseInt(arrivalHour.substring(6,8))),
                    new Time(Integer.parseInt(departureHour.substring(0,2)),
                            Integer.parseInt(departureHour.substring(3,5)), Integer.parseInt(departureHour.substring(6,8)))
            ));
        }
        return stopList;
    }
}