package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.Ride;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/** <h> AllFindRide </h>
 * AllFindRide allows to find ALL Ride objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindRide {

    /**
     * <h> find ALL by connection ID </h>
     * Finds all objects in RIDES table by connection id
     * @param connectionId id of connection
     * @return ArrayList of Ride objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ride> findByConnectionID(Integer connectionId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("RIDES", "CONNECTION_ID", connectionId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ride date (Date Object) </h>
     * Finds all objects in RIDES table by ride date
     * @param rideDate date ride as Date Object
     * @return ArrayList of Ride objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ride> findByRideDate(Date rideDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(rideDate);
            ArrayList<HashMap<String, String>> data = AllFinder.find("RIDES", "RIDE_DATE", date);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ride date (String) </h>
     * Finds all objects in RIDES table by ride date
     * @param rideDate date ride as String
     * @return ArrayList of Ride objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ride> findByRideDate(String rideDate) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("RIDES", "RIDE_DATE", rideDate);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by connection ID & ride date (String) </h>
     * Finds all objects in RIDES table by connection id & ride date
     * @param connectionId id of connection
     * @param rideDate ride date as String
     * @return ArrayList of Ride objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ride> findByConIdRideDate(Integer connectionId, String rideDate) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("RIDES", "RIDE_DATE", rideDate,
                    "CONNECTION_ID", connectionId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by connection ID & ride date (Date Object)</h>
     * Finds all objects in RIDES table by connection id & ride date
     * @param connectionId id of connection
     * @param rideDate ride date as Date Object
     * @return ArrayList of Ride objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ride> findByConIdRideDate(Integer connectionId, Date rideDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(rideDate);
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("RIDES", "RIDE_DATE", date,
                    "CONNECTION_ID", connectionId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<Ride> generateData(ArrayList<HashMap<String, String>> data) {
        try {
            ArrayList<Ride> rideList = new ArrayList<>();
            for (HashMap<String, String> conData : data) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(conData.get("RIDE_DATE"));
                rideList.add(new Ride(
                        Integer.parseInt(conData.get("RIDE_ID")),
                        Integer.parseInt(conData.get("CONNECTION_ID")),
                        date
                ));
            }
            return rideList;
        }
        catch (ParseException e) {
            System.err.format("Parse error: %s\n", e.getMessage());
            return null;
        }
    }
}
