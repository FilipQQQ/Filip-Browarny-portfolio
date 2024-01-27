package app.pociagi.db.Objects;

import app.pociagi.db.Utils.DatabaseHandler;
import app.pociagi.utils.AppData;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * <h2> DBObject </h2>
 * Representation of data from a row from any database table.
 * Database Object is base class for all database objects
 * <p></p>
 * <b>Note:</b> Object is created (by constructors) only locally independently of Database.
 * It can get data from Database but it does not have to. To push object to Database use:
 * object.pushToDB();
 * <p></p>
 * Objects are immutable (only change of nullable attributes is possible)
 * @author rafal
 * @since 2022-12-07
 */
public class DBObject {
    private final DatabaseHandler handler = DatabaseHandler.getInstance();

    private Integer object_id;

    public HashMap<String, Object> data = new HashMap<>();
    public HashMap<String, Object> stringData;
    public String table;
    public String PKColumn;

    public DBObject() {object_id = null;}
    public DBObject(Integer id) {
        object_id = id;
    }

    public DBObject(Integer id, String PKColumn) {
        this.object_id = id;
        this.PKColumn = PKColumn;
    }

    /**
     * <h2> get ID </h2>
     * Every object, that its table has unique id column, should have unique id
     * <p></p>
     * @return ID of object in their table or null if no id in the table
     * Objects are immutable (only change of nullable attributes is possible)
     * @author rafal
     * @since 2022-12-07
     */
    public Integer getID() {
        return object_id;
    }

    public void setID(int newID) {
        this.object_id = newID;
    }


    /**
     * Only used to pushing object that doesn't exist in db
     */
    public void pushToDB() {
        String columnNames = "", columnValues = "";
        for (String key : data.keySet()) {
            //System.out.println(key);
            if (key == "DATE"){
                columnNames = columnNames.concat("\"");
                columnNames = columnNames.concat(key).concat("\", ");
            } else
            columnNames = columnNames.concat(key).concat(", ");
            if (data.get(key).equals("default")) {
                columnValues = columnValues.concat(String.format("%s", data.get(key).toString())).concat(", ");
            } else
            columnValues = columnValues.concat(String.format("'%s'", data.get(key).toString())).concat(", ");
        }
        columnNames = columnNames.substring(0, columnNames.length()-2);
        columnValues = columnValues.substring(0, columnValues.length()-2);

        String query = String.format("INSERT INTO %s (%s) VALUES (%s)",
                table,
                columnNames,
                columnValues);
        //System.out.println(query);
        handler.executeQuery(query);
    }

    /**
     * <h2> edit Object </h2>
     * Edits value of object and writes it to DB
     * <p></p>
     * @param columnName name of column that will change its value
     * @param value new value
     * @author rafal
     * @since 2022-12-23
     */
    public void editObject(String columnName, String value) {
        String query = String.format("UPDATE %s SET %s = '%s' WHERE %s",
                table,
                columnName,
                value.toString(),
                prepareWhereSTMT()
        );
        handler.executeQuery(query);
        data.put(columnName, value);
    }

    /**
     * <h2> delete Object </h2>
     * Removes the object from DB
     * <p></p>
     * @author rafal
     * @since 2022-12-23
     */
    public void deleteObject() {
        String query = String.format("DELETE FROM %s WHERE %s",
                table,
                prepareWhereSTMT()
        );
        handler.executeQuery(query);
        data = null;
    }
//    public void editObject(String columnName, Integer value) {
//        String query = String.format("UPDATE %s SET %s = %s WHERE %s",
//                table,
//                columnName,
//                value.toString(),
//                prepareWhereSTMT()
//        );
//        handler.executeQuery(query);
//        data.put(columnName, value);
//    }

    /**
     * needed for editObject, deleteObject - prepares where statement using all columns
     * (unambiguously defining the object)
     * @return string: where stmt with all columns
     * @author rafal
     * @since 2022-12-23
     */
    private String prepareWhereSTMT() {
        String whereSTMT = "";
        for (String column : data.keySet()) {
            if (data.get(column) instanceof Integer)
                whereSTMT = whereSTMT.concat(String.format("%s = %s", column, data.get(column).toString()));
            else
                whereSTMT = whereSTMT.concat(String.format("%s = '%s'", column, data.get(column).toString()));
            whereSTMT = whereSTMT.concat(" AND ");
        }
        whereSTMT = whereSTMT.substring(0, whereSTMT.length() - 5);
        return whereSTMT;
    }

    /**
     * <h2> toString </h2>
     * Overrides Object's toString function
     * <p></p>
     * @return String containing all values from stringData map
     * @author rafal
     * @since 2022-12-23
     */
    @Override
    public String toString() {
        String out = "";
        for (String key : stringData.keySet()) {
            out = out.concat(stringData.get(key).toString()).concat(" ");
        }
        return out;
    }

    public void removeFromDB(String table, HashMap<String, Object> data) {

    }
}
