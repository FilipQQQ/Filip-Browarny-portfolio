package app.pociagi.db.Objects;

import java.util.HashMap;

/**
 * <h2> User </h2>
 * Representation of data from a row from USERS database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author krzysztof, rafal
 * @since 2022-12-05
 */
public class User extends DBObject {
    private final String login;
    private final String password;
    private final String name;
    private final String surname;

    private String email = null;

    /**
     * <h2> Create User Without Email</h2>
     * Creates User Object without theirs email address
     * @param id ticket id (PK)
     * @param login user's login
     * @param password user's password
     * @param name user's real name
     * @param surname user's real surname
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public User(Integer id, String login, String password, String name, String surname) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        createData();
        createStringData();
    }

    /**
     * <h2> Create User With Email</h2>
     * @param id ticket id (PK)
     * @param login user's login
     * @param password user's password
     * @param name user's real name
     * @param surname user's real surname
     * @param email user's real e-mail address
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public User(Integer id, String login, String password, String name, String surname, String email) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        createData();
        createStringData();
    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("USER_ID", super.getID());
        dict.put("LOGIN", this.login);
        dict.put("NAME", this.name);
        dict.put("SURNAME", this.surname);
        if (this.email!=null) dict.put("EMAIL", this.email);
        super.data = dict;
        super.table = "USERS";
    }

    private void createStringData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("USER_ID", super.getID());
        dict.put("LOGIN", this.login);
        dict.put("NAME", this.name);
        dict.put("SURNAME", this.surname);
        if (this.email!=null) dict.put("EMAIL", this.email);
        super.stringData = dict;
    }

    /**
     * <h2> Push object to Database (Table USERS)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("USER_ID", this.getID());
        dict.put("LOGIN", this.login);
        dict.put("PASSWORD", this.password);
        dict.put("NAME", this.name);
        dict.put("SURNAME", this.surname);
        if (this.email!=null) dict.put("EMAIL", this.email);
        super.data = dict;
        super.table = "USERS";
        super.pushToDB();
    }

    /**
     * <h2> Set E-mail address </h2>
     * Sets user's email address
     * @param email user's email address
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
