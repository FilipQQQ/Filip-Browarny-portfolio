package app.pociagi.db.Objects;

import java.util.HashMap;

/**
 * <h2> Seat </h2>
 * Representation of data from a row from SEATS database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Seat extends DBObject {
    private final Integer carNumber;
    private final Integer seatNumber;
    private final Integer rideId;
    private Boolean isOccupied;
    private Integer ticketId;

    /**
     * <h2> Seat without ticketId </h2>
     * Creates a seat that is not occupied by any ticket
     * @param id seat id (PK)
     * @param rideId ride id (FK)
     * @param carNumber number of car
     * @param seatNumber number of seat in given car
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Seat(Integer id, Integer rideId, Integer carNumber, Integer seatNumber) {
        super(id);
        this.rideId = rideId;
        this.carNumber = carNumber;
        this.seatNumber = seatNumber;
        this.isOccupied = Boolean.FALSE;
        this.ticketId = null;
    }

    /**
     * <h2> Seat with ticketId </h2>
     * Creates a seat that is occupied by ticket with given ticketId
     * @param id seat id (PK)
     * @param rideId ride id (FK)
     * @param carNumber number of car
     * @param seatNumber number of seat in given car
     * @param ticketId id of ticket that occupies this seat
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Seat(Integer id, Integer rideId, Integer carNumber, Integer seatNumber, Integer ticketId) {
        super(id);
        this.ticketId = ticketId;
        this.rideId = rideId;
        this.carNumber = carNumber;
        this.seatNumber = seatNumber;
        if (this.ticketId == null) this.isOccupied = Boolean.FALSE;
        else this.isOccupied = Boolean.TRUE;
    }

    /**
     * <h2> Set Ticket ID </h2>
     * Occupies the seat by ticket with given ticketId
     * @param ticketId id of ticket that occupies this seat
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
        this.isOccupied = Boolean.TRUE;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public Integer getRideId() {
        return rideId;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    /**
     * <h2> Push object to Database (Table SEATS)</h2>
     * Pushes created object to database with set attributes.
     * If no ticketId -> TICKET_ID null in database & IS_OCCUPIED will be false
     * Else IS_OCCUPIED will be true
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("SEAT_ID", this.getID());
        dict.put("RIDE_ID", this.rideId);
        dict.put("CAR_NUMBER", this.carNumber);
        dict.put("SEAT_NUMBER", this.seatNumber);
        if (ticketId!=null) {dict.put("TICKET_ID", this.ticketId);
                            dict.put("IS_OCCUPIED", this.isOccupied);}
        super.data = dict;
        super.table = "SEATS";
        super.pushToDB();
    }
}
