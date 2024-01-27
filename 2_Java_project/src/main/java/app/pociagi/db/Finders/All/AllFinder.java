package app.pociagi.db.Finders.All;

import app.pociagi.db.Utils.DatabaseHandler;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** <h>AllFinder</h>
 * AllFinder is used by all object AllFinders
 * AllFinder allows to find ALL objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFinder {
    public static ArrayList<HashMap<String, String>> findAll(String table) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s", table);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        if (array.size() == 0) throw new SQLException("No object found!");
        else return array;
    }
    //String
    public static ArrayList<HashMap<String, String>> find(String table, String whereColumn, String whereValue) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", table, whereColumn, whereValue);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        if (array.size() == 0) throw new SQLException("No object found!");
        else return array;
    }
    //Integer
    public static ArrayList<HashMap<String, String>> find(String table, String whereColumn, Integer whereValue) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = %d", table, whereColumn, whereValue);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        if (array.size() == 0) throw new SQLException("No object found!");
        else return array;
    }
    // Integer with sorting
    public static ArrayList<HashMap<String, String>> findSort(String table, String whereColumn, Integer whereValue, String sortByColumn) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = %d ORDER BY %s", table, whereColumn, whereValue, sortByColumn);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        if (array.size() == 0) throw new SQLException("No object found!");
        else return array;
    }
    // String, Integer
    public static ArrayList<HashMap<String, String>> findFrom2(String table, String whereColumn, String whereValue,
                                                               String whereColumn2, Integer whereValue2) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s' and %s = %d", table, whereColumn, whereValue,
                whereColumn2, whereValue2);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        return array;
    }
    // Integer, Integer
    public static ArrayList<HashMap<String, String>> findFrom2(String table, String whereColumn, Integer whereValue,
                                                               String whereColumn2, Integer whereValue2) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = %d and %s = %d", table, whereColumn, whereValue,
                whereColumn2, whereValue2);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        if (array.size() == 0) throw new SQLException("No object found!");
        else return array;
    }
    // String, String
    public static ArrayList<HashMap<String, String>> findFrom2(String table, String whereColumn, String whereValue,
                                                               String whereColumn2, String whereValue2) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s' and %s = '%s'", table, whereColumn, whereValue,
                whereColumn2, whereValue2);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<HashMap<String, String>> array = handler.returnAllData(rs);
        if (array.size() == 0) throw new SQLException("No object found!");
        else return array;
    }
}
