package app.pociagi.db.Finders.All;


import app.pociagi.db.Finders.Single.Finder;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.Discount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** <h> AllFindConnection </h>
 * AllFindConnection allows to find ALL Connection objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindConnection {

    /**
     * <h> find ALL by departure station ID </h>
     * Finds all objects in CONNECTIONS table by departure station ID
     * @param depStationId id of departure station
     * @return ArrayList of Connection objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Connection> findByDepartureStationID(Integer depStationId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("CONNECTIONS", "DEPARTURE_STATION", depStationId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by arrival station ID </h>
     * Finds all objects in CONNECTIONS table by arrival station ID
     * @param arrStationId id of arrival station
     * @return ArrayList of Connection objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Connection> findByArrivalStationID(Integer arrStationId) {

        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("CONNECTIONS", "ARRIVAL_STATION", arrStationId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
    public static ArrayList<Connection> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("CONNECTIONS");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            System.out.println("test");
            return null;
        }
    }
    private static ArrayList<Connection> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<Connection> connectionList = new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            connectionList.add(new Connection(
                    Integer.parseInt(conData.get("CONNECTION_ID")),
                    Integer.parseInt(conData.get("DEPARTURE_STATION")),
                    Integer.parseInt(conData.get("ARRIVAL_STATION"))
            ));
        }
        return connectionList;
    }

}
