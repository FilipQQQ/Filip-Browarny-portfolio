package app.pociagi.db.Finders.Single;

import app.pociagi.db.Utils.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** <h>Finder</h>
 * Finder is used by all object Finders
 * Finder allows to find SINGLE object that fulfills where stmt
 * @author rafal
 * @since 2022-12-08
 */
public class Finder {
    public static String find(String table, String column, String whereColumn, String whereValue) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                column, table, whereColumn, whereValue);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 1);
        if (arr.size() == 1) return arr.get(0);
        else throw new SQLException("Returned too much data!");
    }
    public static String find(String table, String column, String whereColumn, Integer whereValue) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT %s FROM %s WHERE %s = %d",
                column, table, whereColumn, whereValue);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 1);
        if (arr.size() == 1) return arr.get(0);
        else throw new SQLException("Returned too much data!");
    }
    public static String findFrom2(String table, String column, String whereColumn1, String whereValue1,
                                   String whereColumn2, String whereValue2) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s' and %s = '%s'",
                column, table, whereColumn1, whereValue1, whereColumn2, whereValue2);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 1);
        if (arr.size() == 1) return arr.get(0);
        else throw new SQLException("Returned too much data!");
    }
    public static String findFrom2(String table, String column, String whereColumn1, String whereValue1,
                                   String whereColumn2, Integer whereValue2) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s' and %s = %d",
                column, table, whereColumn1, whereValue1, whereColumn2, whereValue2);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 1);
        if (arr.size() == 1) return arr.get(0);
        else throw new SQLException("Returned too much data!");
    }

    public static String findFrom2(String table, String column, String whereColumn1, Integer whereValue1,
                                   String whereColumn2, Integer whereValue2) throws SQLException {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String sql = String.format("SELECT %s FROM %s WHERE %s = %d and %s = %d",
                column, table, whereColumn1, whereValue1, whereColumn2, whereValue2);
        //System.out.println(sql);
        ResultSet rs = handler.executeQuery(sql);
        ArrayList<String> arr = handler.returnDataArray(rs, 1);
        if (arr.size() == 1) return arr.get(0);
        else throw new SQLException("Returned too much data!");
    }
}
