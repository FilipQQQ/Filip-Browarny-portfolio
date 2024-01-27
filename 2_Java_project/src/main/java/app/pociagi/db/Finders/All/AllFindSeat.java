package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.Seat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** <h> AllFindSeat </h>
 * AllFindSeat allows to find ALL Seat objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindSeat {

    /**
     * <h> find ALL by ride ID </h>
     * Finds all objects in SEATS table by ride ID
     * @param rideId id of ride
     * @return ArrayList of Seat objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Seat> findByRideID(Integer rideId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("SEATS", "RIDE_ID", rideId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find ALL by ticket ID </h>
     * Finds all objects in SEATS table by ticket id
     * @param ticketId id of ticket
     * @return ArrayList of Seat objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Seat> findByTicketID(Integer ticketId) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("SEATS", "TICKET_ID", ticketId);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<Seat> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<Seat> seatList= new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            seatList.add(new Seat(
                    Integer.parseInt(conData.get("SEAT_ID")),
                    Integer.parseInt(conData.get("RIDE_ID")),
                    Integer.parseInt(conData.get("CAR_NUMBER")),
                    Integer.parseInt(conData.get("SEAT_NUMBER")),
                    Integer.parseInt(conData.get("TICKET_ID"))
            ));
        }
        return seatList;
    }
}
