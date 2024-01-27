package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Seat;

import java.sql.SQLException;

/** <h>FindSeat</h>
 * FindSeat allows to find Seat by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindSeat {

    /** <h> find by seat ID</h>
     * Finds object in SEATS table by seat ID
     * @param id id of seat
     * @return Seat object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Seat findByID(Integer id) {
        try {
            Integer rideId = Integer.parseInt(Finder.find("SEATS", "RIDE_ID", "SEAT_ID", id));
            Integer carNumber = Integer.parseInt(Finder.find("SEATS", "CAR_NUMBER", "SEAT_ID", id));
            Integer seatNumber = Integer.parseInt(Finder.find("SEATS", "SEAT_NUMBER", "SEAT_ID", id));
            Integer ticketId = Integer.parseInt(Finder.find("SEATS", "TICKET_ID", "SEAT_ID", id));
            return new Seat(id, rideId, carNumber, seatNumber, ticketId);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /** <h> find by ticket ID</h>
     * Finds object in SEATS table by ticket ID
     * @param ticketId id of ticket we want to search seat assigned to
     * @return Seat object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Seat findByTicketID(Integer ticketId) {
        try {
            Integer id = Integer.parseInt(Finder.find("SEATS", "SEAT_ID", "TICKET_ID", ticketId));
            Integer rideId = Integer.parseInt(Finder.find("SEATS", "RIDE_ID", "TICKET_ID", ticketId));
            Integer carNumber = Integer.parseInt(Finder.find("SEATS", "CAR_NUMBER", "TICKET_ID", ticketId));
            Integer seatNumber = Integer.parseInt(Finder.find("SEATS", "SEAT_NUMBER", "TICKET_ID", ticketId));
            return new Seat(id, rideId, carNumber, seatNumber, ticketId);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

}
