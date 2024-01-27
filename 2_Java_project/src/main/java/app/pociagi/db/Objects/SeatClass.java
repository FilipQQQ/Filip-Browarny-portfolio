package app.pociagi.db.Objects;

import java.util.HashMap;

/**
 * <h2> SeatClass </h2>
 * Representation of data from a row from CLASSES database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class SeatClass extends DBObject{
    private final String name;
    private final Integer coef;

    /**
     * <h2> Create Seat Class Object </h2>
     * Creates a seat class object
     * @param id class id (PK)
     * @param name name of class that will be displayed
     * @param coef multiplies ticket price (ex. coef = 2 -> ticket_price*2)
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public SeatClass(Integer id, String name, Integer coef) {
        super(id);
        this.name = name;
        this.coef = coef;
        createData();
        createStringData();
    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("ID", super.getID());
        dict.put("NAME", this.name);
        dict.put("COEF", this.coef);
        super.data = dict;
        super.table = "DISCOUNTS";
    }

    private void createStringData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("ID", this.getID());
        dict.put("NAME", this.name);
        super.stringData = dict;
    }

    public String getName() {
        return name;
    }

    public Integer getCoef() {
        return coef;
    }

    /**
     * <h2> Push object to Database (Table CLASSES)</h2>
     * Pushes created object to database with set attributes.
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    @Override
    public void pushToDB() {
        super.pushToDB();
    }
}
