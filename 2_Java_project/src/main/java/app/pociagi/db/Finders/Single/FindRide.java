package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Ride;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** <h> FindRide </h>
 * FindRide allows to find Ride by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindRide {

    /**
     * <h> find by ride ID </h>
     * Finds object in RIDES table by ride id
     * @param rideId id of ride
     * @return Ride object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Ride findByID(Integer rideId) {
        try {
            Integer connectionId = Integer.parseInt(Finder.find("RIDES", "CONNECTION_ID", "RIDE_ID", rideId));
            String rideDate = Finder.find("RIDES", "RIDE_DATE", "RIDE_ID", rideId).substring(0,10);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(rideDate);
            return new Ride(rideId, connectionId, date);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        } catch (ParseException e) {
            System.err.format("Parse exception: %s\n", e.getMessage());
            return null;
        }
    }

    /**
     * <h> find by ride connection ID & date </h>
     * Finds object in RIDES table by connection id & ride date (Date object)
     * @param connectionId id of connection
     * @param rideDate Date object!
     * @return Ride object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Ride findByConIdRideDate(Integer connectionId, Date rideDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(rideDate);
            Integer rideId = Integer.parseInt(Finder.findFrom2("RIDES", "RIDE_ID",
                    "RIDE_DATE", date, "CONNECTION_ID", connectionId));
            return new Ride(rideId, connectionId, rideDate);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find by ride connection ID & date </h>
     * Finds object in RIDES table by connection id & ride date (String "yyyy-MM-dd")
     * @param connectionId id of connection
     * @param rideDate String formatted "yyyy-MM-dd" ex. "2022-12-01"
     * @return Ride object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Ride findByConIdRideDate(Integer connectionId, String rideDate) {
        try {
            Integer rideId = Integer.parseInt(Finder.findFrom2("RIDES", "RIDE_ID",
                    "RIDE_DATE", rideDate, "CONNECTION_ID", connectionId));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(rideDate);
            return new Ride(rideId, connectionId, date);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        } catch (ParseException e) {
            System.err.format("Parse error: %s\n", e.getMessage());
            return null;
        }
    }
}