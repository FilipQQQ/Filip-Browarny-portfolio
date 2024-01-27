package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.SeatClass;

import java.sql.SQLException;

/** <h> FindSeatClass </h>
 * FindSeatClass allows to find SeatClass by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindSeatClass {

    /**
     * <h> find by seat class id </h>
     * Finds object in CLASSES table by seat class id
     *
     * @param classId id of seat class
     * @return SeatClass object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static SeatClass findByID(Integer classId) {
        try {
            String name = Finder.find("CLASSES", "NAME", "ID", classId);
            Integer coef = Integer.parseInt(Finder.find("CLASSES", "COEF", "ID", classId));
            return new SeatClass(classId, name, coef);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find by seat class name </h>
     * Finds object in CLASSES table by seat class name
     * @param name name of the seat class that is displayed
     * @return SeatClass object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static SeatClass findByName(String name) {
        try {
            Integer classId = Integer.parseInt(Finder.find("CLASSES", "ID", "NAME", name));
            Integer coef = Integer.parseInt(Finder.find("CLASSES", "COEF", "NAME", name));
            return new SeatClass(classId, name, coef);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}