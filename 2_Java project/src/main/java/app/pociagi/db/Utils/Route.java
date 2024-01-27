package app.pociagi.db.Utils;

import app.pociagi.db.Finders.All.AllFindStop;
import app.pociagi.db.Finders.Single.FindConnection;
import app.pociagi.db.Finders.Single.FindStation;
import app.pociagi.db.Objects.Connection;
import app.pociagi.db.Objects.ConnectionStop;
import app.pociagi.db.Objects.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <h2> Route </h2>
 * Representation of route - combination of Connection and Stations that occur on it.
 * Stop list is sorted by arrival hour.
 * <p></p>
 * @author rafal
 * @since 2022-12-10
 */
public class Route {
    private final Connection connection;
    private final ArrayList<ConnectionStop> stopList;
    private final ArrayList<Station> stationList;

    /**
     * <h2> Create Route Object (Connection object) </h2>
     * Creates a route object, it finds all stations at given Connection using Finders.
     * @param connection Connection object
     * <p>
     * @author rafal
     * @since 2022-12-10
     */
    public Route(Connection connection) {
        this.connection = connection;
        this.stopList = AllFindStop.findByConnectionID(connection.getID());
        Collections.sort(stopList, Comparator.comparing(s -> s.getArrivalHour()));
        ArrayList<Station> stList = new ArrayList<>();
        for (ConnectionStop stop : this.stopList) {
            stList.add(FindStation.findById(stop.getStationId()));
        }
        this.stationList = stList;
    }

    /**
     * <h2> Create Route Object (Connection ID) </h2>
     * Creates a route object, it finds all stations at given Connection using Finders.
     * @param connectionId Connection ID
     * <p>
     * @author rafal
     * @since 2022-12-10
     */
    public Route(Integer connectionId) {
        this.connection = FindConnection.findByID(connectionId);
        this.stopList = AllFindStop.findByConnectionID(connectionId);
        Collections.sort(stopList, Comparator.comparing(s -> s.getArrivalHour()));
        ArrayList<Station> stList = new ArrayList<>();
        for (ConnectionStop stop : this.stopList) {
            stList.add(FindStation.findById(stop.getStationId()));
        }
        this.stationList = stList;
    }

    public Route(Connection connection, Integer startStationId, Integer StopStationId) {
        this.stationList = null;
        this.connection = null;
        this.stopList = null;
        // to do
    }

    /**
     * <h2> Is station at Route? (by ID) </h2>
     * Checks whether station with given ID occurs at the Route.
     * @param stationId
     * @return TRUE/FALSE (Station occurs/does not occur)
     * <p>
     * @author rafal
     * @since 2022-12-10
     */
    public Boolean isStationAtRoute(Integer stationId) {
        for (Station station : this.stationList) {
            if (station.getID() == stationId) return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * <h2> Is station at Route? (by name) </h2>
     * Checks whether station with given name occurs at the Route.
     * @param stationName
     * @return TRUE/FALSE (Station occurs/does not occur)
     * <p>
     * @author rafal
     * @since 2022-12-10
     */
    public Boolean isStationAtRoute(String stationName) {
        for (Station station : this.stationList) {
            if (station.getName() == stationName) return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public ConnectionStop getStop(String name) {
        for (int i = 0; i < this.stationList.size(); i++) {
            //System.out.println(this.stationList.get(i).getName());
            if (name.equals(this.stationList.get(i).getName())) {
                return this.stopList.get(i);
            }
        }
        return null;
    }

    public ArrayList<ConnectionStop> getStopList() {
        return stopList;
    }

    public ArrayList<Station> getStationList() {
        return stationList;
    }

    public Connection getConnection() {
        return connection;
    }
}
