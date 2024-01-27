package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.User;

import java.sql.SQLException;

/** <h>FindUser</h>
 * FindUser allows to find User by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindUser{

    /** <h> find by user login </h>
     * Finds object in USERS table by user login
     * @param login login of user
     * @return User object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static User findByLogin(String login) {
        try {
            Integer userId = Integer.parseInt(Finder.find("USERS", "USER_ID", "LOGIN", login));
            String password = Finder.find("USERS", "PASSWORD", "LOGIN", login);
            String name = Finder.find("USERS", "NAME", "LOGIN", login);
            String surname = Finder.find("USERS", "SURNAME", "LOGIN", login);
            String email = Finder.find("USERS", "EMAIL", "LOGIN", login);
            return new User(userId, login, password, name, surname, email);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /** <h> find by user ID </h>
     * Finds object in USERS table by user ID
     * @param userId id of user
     * @return User object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static User findByID(Integer userId) {
        try {
            String login = Finder.find("USERS", "LOGIN", "USER_ID", userId);
            String password = Finder.find("USERS", "PASSWORD", "USER_ID", userId);
            String name = Finder.find("USERS", "NAME", "USER_ID", userId);
            String surname = Finder.find("USERS", "SURNAME", "USER_ID", userId);
            String email = Finder.find("USERS", "EMAIL", "USER_ID", userId);
            return new User(userId, login, password, name, surname, email);
        }
        catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}