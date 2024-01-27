package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Station;

import java.sql.SQLException;

/** <h>FindStation</h>
 * FindStation allows to find Station by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindStation{

    /** <h> find by station name </h>
     * Finds object in STATIONS table by station name
     * @param name station name that is displayed
     * @return Station object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Station findByName(String name) {
        try {
            return new Station(Integer.parseInt(Finder.find("STATIONS", "STATION_ID",
                    "NAME", name)), name, Integer.parseInt(Finder.find("STATIONS", "CONNECTION_STATION",
                    "NAME", name)));
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /** <h> find by station ID </h>
     * Finds object in STATIONS table by station id
     * @param stationId id of station
     * @return Station object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Station findById(Integer stationId) {
        try {
            return new Station(stationId, Finder.find("STATIONS", "NAME",
                    "STATION_ID", stationId), Integer.parseInt(Finder.find("STATIONS", "CONNECTION_STATION",
                    "STATION_ID", stationId)));
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}
