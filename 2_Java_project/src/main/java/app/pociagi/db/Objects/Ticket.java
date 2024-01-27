package app.pociagi.db.Objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * <h2> Ticket </h2>
 * Representation of data from a row from TICKETS database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Ticket extends DBObject{

    private final Integer departureStationId;
    private final Integer arrivalStationId;
    private final Integer connectionId;
    private final Date date;
    private Integer userId = null;

    private Integer discountId = null;

    private Integer carId = null;

    private Integer price = null;
    private Integer seatId = null;
    private String name = null;
    private String surname = null;


    /**
     * <h2> Create Ticket </h2>
     * @param id ticket id (PK)
     * @param connectionId connection id (FK)
     * @param date date in Date format
     * @param departureStationId departure station id (FK)
     * @param arrivalStationId arrival station id (FK)
     * @param userId user id (FK)
     * @param discountId discount id (FK)
     * @param carId car number
     * @param seatId seat number
     * @param price price of ticket
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Ticket(Integer id,
                  Integer connectionId,
                  Date date,
                  Integer departureStationId,
                  Integer arrivalStationId,
                  Integer userId,
                  Integer discountId,
                  Integer carId,
                  Integer seatId,
                  Integer price) {
        super(id);
        this.connectionId = connectionId;
        this.date = date;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.userId = userId;
        this.discountId = discountId;
        this.carId = carId;
        this.price = price;
        this.seatId = seatId;
        createData();
        createStringData();
    }

//    /**
//     * <h2> Ticket with name and surname </h2>
//     * @param id ticket id (PK)
//     * @param rideId ride id (FK)
//     * @param departureStationId departure station id (FK)
//     * @param arrivalStationId arrival station id (FK)
//     * @param name buyer's name
//     * @param surname buyer's surname
//     * <p>
//     * @author rafal
//     * @since 2022-12-07
//     */
//    public Ticket(Integer id,
//                  Integer rideId,
//                  Integer departureStationId,
//                  Integer arrivalStationId,
//                  String name,
//                  String surname) {
//        super(id);
//        this.rideId = rideId;
//        this.departureStationId = departureStationId;
//        this.arrivalStationId = arrivalStationId;
//        this.name = name;
//        this.surname = surname;
//        createData();
//        createStringData();
//    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("TICKET_ID", this.getID());
        dict.put("CONNECTION_ID", this.connectionId);
        dict.put("ID_DEPARTURE_STATION", this.departureStationId);
        dict.put("ID_ARRIVAL_STATION", this.arrivalStationId);
        if (userId!=null) dict.put("USER_ID", this.userId);
        dict.put("DISCOUNT_ID", this.discountId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = this.date;
        dict.put("TICKET_DATE", dateFormat.format(date));
        dict.put("CAR_ID", this.carId);
        dict.put("SEAT_ID", this.seatId);
        dict.put("PRICE", this.price);
        super.data = dict;
        super.table = "TICKETS";
    }

    private void createStringData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("TICKET_ID", this.getID());
        if (userId!=null) dict.put("USER_ID", this.userId);
        if (name!=null) dict.put("NAME", this.name);
        if (surname!=null) dict.put("SURNAME", this.surname);
        super.stringData = dict;
    }

    /**
     * <h2> Push object to Database (Table TICKETS)</h2>
     * Pushes created object to database with set attributes.
     * If no userId -> USER_ID null in database
     * etc.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        createData();
        if (this.getID() == null)
            super.data.put("TICKET_ID", "default");
        super.pushToDB();
    }

    /**
     * <h2> Set Name </h2>
     * Sets Ticket buyer's name
     * @param name buyer's name
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setName(String name) {

        this.name = name;
        createData();
        createStringData();
    }

    /**
     * <h2> Set Discount ID </h2>
     * Sets Ticket discount ID
     * @param discountID
     * <p>
     * @author rafal
     * @since 2023-01-04
     */
    public void setDiscountId(Integer discountID) {
        this.discountId = discountID;
    }

    /**
     * <h2> Set Surname </h2>
     * Sets Ticket buyer's surname
     * @param surname buyer's surname
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setSurname(String surname) {

        this.surname = surname;
        createData();
        createStringData();
    }

    /**
     * <h2> Set User ID </h2>
     * Sets user id that buys the ticket (if buyer has an account)
     * @param userId buyer's user id
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
        createData();
        createStringData();
    }

    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate() {
        return date;
    }

    public Integer getCarId() {
        return carId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }
}