package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.ConnectionStop;

import java.sql.SQLException;
import java.sql.Time;

/** <h>FindStop</h>
 * FindStop allows to find Stop by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindStop {

    /**
     * <h> find by connection ID & station ID </h>
     * Finds object in STOPS table by station name
     * @param connectionId id of connection
     * @param stationId id of station
     * @return ConnectionStop object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static ConnectionStop findByConIdStationId(Integer connectionId, Integer stationId) {
        try {
            String arrivalHour = Finder.findFrom2("STOPS", "ARRIVAL_HOUR",
                    "CONNECTION_ID", connectionId, "STATION_ID", stationId).substring(11);
            String departureHour = Finder.findFrom2("STOPS", "DEPARTURE_HOUR",
                    "CONNECTION_ID", connectionId, "STATION_ID", stationId).substring(11);
            Time arrTime = new Time(Integer.parseInt(arrivalHour.substring(0,2)),
                    Integer.parseInt(arrivalHour.substring(3,5)), Integer.parseInt(arrivalHour.substring(6,8)));
            Time depTime = new Time(Integer.parseInt(departureHour.substring(0,2)),
                    Integer.parseInt(departureHour.substring(3,5)), Integer.parseInt(departureHour.substring(6,8)));
            return new ConnectionStop(connectionId, stationId, arrTime, depTime);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}