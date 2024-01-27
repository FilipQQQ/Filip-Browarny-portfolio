package app.pociagi.db.Objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * <h2> Ride </h2>
 * Representation of data from a row from RIDES database table.
 * (Run of train on given connection on given date)
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Ride extends DBObject{
    private final Integer connectionId;
    private final Date ride_date;

    /**
     * <h2> Create Ride Object </h2>
     * Creates a ride object (Run of train on given connection on given date)
     * @param id ride id (PK)
     * @param connectionId connection id (FK) provided to get route
     * @param ride_date date when rides takes place
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Ride(Integer id, Integer connectionId, Date ride_date) {
        super(id);
        this.connectionId = connectionId;
        this.ride_date = ride_date;
    }

    /**
     * <h2> Push object to Database (Table RIDES)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("ID", this.getID());
        dict.put("CONNECTION_ID", this.connectionId);
        dict.put("RIDE_DATE", this.ride_date);
        super.data = dict;
        super.table = "RIDES";
        super.pushToDB();
    }

    public Date getRideDate() {
        return ride_date;
    }

    public String getRideDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = this.ride_date;
        return dateFormat.format(date);
    }

    public Integer getConnectionId() {
        return connectionId;
    }
}
