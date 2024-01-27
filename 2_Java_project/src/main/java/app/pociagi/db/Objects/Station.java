package app.pociagi.db.Objects;

import java.util.HashMap;

/**
 * <h2> Station </h2>
 * Representation of data from a row from STATIONS database table.
 * Through one station can surpass many trains (connections)
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Station extends DBObject{
    private final String name;

    private final Integer connectionStation;

    /**
     * <h2> Create Station Object </h2>
     * Creates a station object
     * @param id station id (PK)
     * @param name name of station that will be displayed
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Station(Integer id, String name, Integer connectionStation) {
        super(id);
        this.name = name;
        this.connectionStation = connectionStation;
        createData();
        createStringData();
    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("STATION_ID", super.getID());
        dict.put("NAME", this.name);
        dict.put("CONNECTION_STATION", this.connectionStation);
        super.data = dict;
        super.table = "STATIONS";
    }

    private void createStringData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("STATION_ID", this.getID());
        dict.put("NAME", this.name);
        dict.put("CONNECTION_STATION", this.connectionStation);
        super.stringData = dict;
    }
    public String getName() {
        return name;
    }

    public int getConnectionStation() {
        return connectionStation;
    }

    /**
     * <h2> Push object to Database (Table STATIONS)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {super.pushToDB();}
}
