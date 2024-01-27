package app.pociagi.db.Objects;

import java.util.HashMap;

/**
 * <h2> Discount </h2>
 * Representation of data from a row from DISCOUNTS database table.
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class Discount extends DBObject{
    private final String name;
    private final Integer value;

    /**
     * <h2> Create Discount Object </h2>
     * Creates a discount object
     * @param id discount id (PK)
     * @param name name of discount that will be displayed
     * @param value how much discount is (%)
     * <p>
     * @author rafal
     * @since 2022-12-07
     */
    public Discount(Integer id, String name, Integer value) {
        super(id, "ID");
        this.name = name;
        this.value = value;
        createData();
        createStringData();
    }

    private void createData() {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("ID", super.getID());
        dict.put("NAME", this.name);
        dict.put("VALUE", this.value);
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
    public Integer getValue() {
        return value;
    }

    /**
     * <h2> Push object to Database (Table DISCOUNTS)</h2>
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
