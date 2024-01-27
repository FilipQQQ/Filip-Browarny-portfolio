package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.Discount;
import app.pociagi.db.Objects.Ticket;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/** <h> AllFindTicket </h>
 * AllFindTicket allows to find ALL Ticket objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindTicket {

    /**
     * <h> find ALL by ride ID </h>
     * Finds all objects in TICKETS table on ride with ride id
     * @param rideId id of ride
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByRideID(Integer rideId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("TICKETS", "RIDE_ID", rideId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by user ID </h>
     * Finds all objects in TICKETS table with user id (all user's tickets)
     * @param userId id of user
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByUserID(Integer userId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("TICKETS", "USER_ID", userId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ride ID & departure station ID</h>
     * Finds all objects in TICKETS table on ride with ride id & departure station id
     * @param rideId id of ride
     * @param depStatId station id of departure
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByRideIdDepartureStatID(Integer rideId, Integer depStatId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("TICKETS", "RIDE_ID", rideId,
            "ID_DEPARTURE_STATION", depStatId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ride ID & arrival station ID </h>
     * Finds all objects in TICKETS table on ride with ride id & arrival station id
     * @param rideId id of ride
     * @param arrStatId station id of arrival
     * @return ArrayList of Ticket objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Ticket> findByRideIdArrivalStatID(Integer rideId, Integer arrStatId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("TICKETS", "RIDE_ID", rideId,
                    "ID_ARRIVAL_STATION", arrStatId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    public static ArrayList<Ticket> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("TICKETS");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<Ticket> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        if (data == null) return ticketList;
        for (HashMap<String, String> conData : data) {
            Integer ticketId = Integer.parseInt(conData.get("TICKET_ID"));
            Integer conId = Integer.parseInt(conData.get("CONNECTION_ID"));
            Integer depStatId = Integer.parseInt(conData.get("ID_DEPARTURE_STATION"));
            Integer arrStatId = Integer.parseInt(conData.get("ID_ARRIVAL_STATION"));
            Integer userId;
            try {
                userId = Integer.parseInt(conData.get("USER_ID"));
            } catch (NumberFormatException e) {
                userId = null;
            }
            Date date;
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                date = formatter.parse(conData.get("TICKET_DATE"));
            } catch (ParseException e) {
                System.err.format("Parse error: %s\n", e.getMessage());
                return null;
            }
            Integer discountId = Integer.parseInt(conData.get("DISCOUNT_ID"));
            Integer carId = Integer.parseInt(conData.get("CAR_ID"));
            Integer seatId = Integer.parseInt(conData.get("SEAT_ID"));
            Integer price = Integer.parseInt(conData.get("PRICE"));
            ticketList.add(new Ticket(
                    ticketId,
                    conId,
                    date,
                    depStatId,
                    arrStatId,
                    userId,
                    discountId,
                    carId,
                    seatId, price));
        }
        return ticketList;
    }

    public static ArrayList<Ticket> findByConIDdate(Integer connectionID, Date date) {
        // to do
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = dateFormat.format(date);
            ArrayList<HashMap<String, String>> data = AllFinder.findFrom2("TICKETS",
                    "TICKET_DATE",
                    dateStr,
                    "CONNECTION_ID", connectionID);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}
