package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.Discount;
import app.pociagi.db.Objects.SeatClass;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** <h> AllFindSeatClass </h>
 * AllFindSeatClass allows to find ALL SeatClass objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindSeatClass {

    /**
     * <h> find ALL by coef </h>
     * Finds all objects in CLASSES table by coef
     * @param coef coef of seat class
     * @return ArrayList of SeatClass objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<SeatClass> findByCoef(Integer coef) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("CLASSES", "COEF", coef);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    public static ArrayList<SeatClass> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("CLASSES");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    private static ArrayList<SeatClass> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<SeatClass> classesList= new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            classesList.add(new SeatClass(
                    Integer.parseInt(conData.get("ID")),
                    conData.get("NAME"),
                    Integer.parseInt(conData.get("COEF"))
            ));
        }
        return classesList;
    }
}
