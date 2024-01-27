package app.pociagi.db.Objects;

import app.pociagi.db.Finders.Single.FindStation;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

/**
 * <h2> Connection Stop </h2>
 * Representation of data from a row from STOPS database table.
 * Connection Stop objects provide information about by what Stations Connection is routed.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class ConnectionStop extends DBObject{
    private Integer connectionId;
    private Integer stationId;

    private Time arrivalHour;
    private Time departureHour;
    /**
     * <h2> Create Connection Stop object </h2>
     * Creates a connection stop object
     * @param connectionId connection id (PK/FK)
     * @param stationId station id (PK/FK)
     * @param arrivalHour time of arrival
     * @param departureHour time of departure
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public ConnectionStop(Integer connectionId, Integer stationId, Time arrivalHour, Time departureHour) {
        super();
        this.connectionId = connectionId;
        this.stationId = stationId;
        this.arrivalHour = arrivalHour;
        this.departureHour = departureHour;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public Time getArrivalHour() {
        return arrivalHour;
    }

    public Time getDepartureHour() {
        return departureHour;
    }

    /**
     * <h2> Push object to Database (Table STOPS)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("CONNECTION_ID", this.connectionId);
        dict.put("STATION_ID", this.stationId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = this.arrivalHour;
        dict.put("ARRIVAL_HOUR", dateFormat.format(date));
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = this.departureHour;
        dict.put("DEPARTURE_HOUR", dateFormat.format(date2));
        super.data = dict;
        super.table = "STOPS";
//        if(this.stationId == null) {
//            super.data.put("STATION_ID", "default");
//        }
//        if(this.connectionId == null) {
//            super.data.put("CONNECTION_ID", "default");
//        }
        super.pushToDB();
    }

    public void setConnectionID(int newID) {
        this.connectionId = newID;
    }

    @Override
    public String toString() {
        String out = FindStation.findById(this.stationId).toString() + " " + this.arrivalHour.toString()+ " " + this.departureHour.toString();
//        out.concat(FindStation.findById(this.stationId).toString().concat(" "));
//        out.concat(this.arrivalHour.toString().concat(" "));
//        out.concat(this.departureHour.toString());
        return out;
    }

//    public static void main(String[] args) {
//        LocalTime timeArrival = LocalTime.parse("12:12");
//        LocalTime timeDeparture = LocalTime.parse("12:14");
//        Time tArr = Time.valueOf(timeArrival);
//        Time tDep = Time.valueOf(timeDeparture);
//        ConnectionStop s= new ConnectionStop(13, 4, tArr, tDep);
//        System.out.println(s);
//
//    }
}



