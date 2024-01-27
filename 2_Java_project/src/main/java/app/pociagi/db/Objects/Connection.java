package app.pociagi.db.Objects;

import app.pociagi.db.Finders.Single.FindStation;

import java.util.HashMap;

/**
 * <h2> Connection </h2>
 * Representation of data from a row from CONNECTIONS database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Connection extends DBObject{
    private Integer departureStationId;
    private Integer arrivalStationId;

    /**
     * <h2> Create Connection Object </h2>
     * Creates a connection object
     * @param id discount id (PK)
     * @param departureStationId id of departure station (FK)
     * @param arrivalStationId id of arrival station (FK)
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Connection(Integer id, Integer departureStationId, Integer arrivalStationId) {
        super(id);
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        createData();
        createStringData();
    }

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public void setDepartureStationID(int newID) {
        this.departureStationId = newID;
    }

    public void setArrivalStationId(int newID) {
        this.arrivalStationId = newID;
    }


    /**
     * <h2> Push object to Database (Table CONNECTIONS)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", this.getID());
        dict.put("DEPARTURE_STATION", this.departureStationId);
        dict.put("ARRIVAL_STATION", this.arrivalStationId);
        super.data = dict;
        super.table = "CONNECTIONS";
        if(this.getID() == null) {
            super.data.put("CONNECTION_ID", "default");
        }
        super.pushToDB();
    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", super.getID());
        dict.put("DEPARTURE_STATION", this.departureStationId);
        dict.put("ARRIVAL_STATION", this.arrivalStationId);
        super.data = dict;
        super.table = "CONNECTIONS";
    }

    private void createStringData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", this.getID());
        dict.put("DEPARTURE_STATION", this.departureStationId);
        dict.put("ARRIVAL_STATION", this.arrivalStationId);
        super.stringData = dict;
    }
}
