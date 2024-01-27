package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Connection;

import java.sql.SQLException;

/** <h> FindConnection </h>
 * FindConnection allows to find Connection by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindConnection {

    /**
     * <h> find by connection ID </h>
     * Finds object in CONNECTIONS table by connection id
     * @param connectionId id of connection
     * @return Connection object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Connection findByID(Integer connectionId) {
        try {
            Integer arrivalId = Integer.parseInt(Finder.find("CONNECTIONS", "ARRIVAL_STATION", "CONNECTION_ID", connectionId));
            Integer departId = Integer.parseInt(Finder.find("CONNECTIONS", "DEPARTURE_STATION", "CONNECTION_ID", connectionId));
            return new Connection(connectionId, departId, arrivalId);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}