package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Ticket;

import java.sql.SQLException;

/** <h>FindTicket</h>
 * FindUser allows to find Ticket by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindTicket{

    /** <h> find by ticket ID </h>
     * Finds object in TICKETS table by ticket id
     * @param ticketId id of ticket
     * @return Ticket object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Ticket findByID(Integer ticketId) {
        try {
            Integer rideId = Integer.parseInt(Finder.find("TICKETS", "RIDE_ID", "TICKET_ID", ticketId));
            Integer depStatId = Integer.parseInt(Finder.find("TICKETS", "ID_DEPARTURE_STATION", "TICKET_ID", ticketId));
            Integer arrStatId = Integer.parseInt(Finder.find("TICKETS", "ID_ARRIVAL_STATION", "TICKET_ID", ticketId));
            Integer userId = Integer.parseInt(Finder.find("TICKETS", "USER_ID", "TICKET_ID", ticketId));

            //if (userId == null) return new Ticket(ticketId, rideId, depStatId, arrStatId, name, surname);
            //else return new Ticket(ticketId, rideId, depStatId, arrStatId, userId);
            return null;
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /** <h> find by user ID </h>
     * Finds object in TICKETS table by user ID
     * @param userId id of user assigned to ticket
     * @return Ticket object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Ticket findByUserID(Integer userId) {
        try {
            Integer ticketId = Integer.parseInt(Finder.find("TICKETS", "TICKET_ID", "USER_ID", userId));
            Integer rideId = Integer.parseInt(Finder.find("TICKETS", "RIDE_ID", "USER_ID", userId));
            Integer depStatId = Integer.parseInt(Finder.find("TICKETS", "ID_DEPARTURE_STATION", "USER_ID", userId));
            Integer arrStatId = Integer.parseInt(Finder.find("TICKETS", "ID_ARRIVAL_STATION", "USER_ID", userId));
            //return new Ticket(ticketId, rideId, depStatId, arrStatId, userId);
            return null;
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}