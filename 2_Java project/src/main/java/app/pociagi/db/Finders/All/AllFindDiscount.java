package app.pociagi.db.Finders.All;


import app.pociagi.db.Objects.Discount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** <h> AllFindDiscount </h>
 * AllFindDiscount allows to find ALL Discount objects that fulfill where statement
 * @author rafal
 * @since 2022-12-08
 */
public class AllFindDiscount {

    /**
     * <h> find ALL by discount value </h>
     * Finds all objects in DISCOUNTS table by value of discount
     * @param value value of discount in (%)
     * @return ArrayList of Discount objects or null if it cannot find the object
     * @author rafal
     * @since 2022-12-08
     */
    public static ArrayList<Discount> findByValue(Integer value) {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.find("DISCOUNTS", "VALUE", value);
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    public static ArrayList<Discount> getAll() {
        try {
            ArrayList<HashMap<String, String>> data = AllFinder.findAll("DISCOUNTS");
            return generateData(data);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
    private static ArrayList<Discount> generateData(ArrayList<HashMap<String, String>> data) {
        ArrayList<Discount> discountList= new ArrayList<>();
        for (HashMap<String, String> conData : data) {
            discountList.add(new Discount(
                    Integer.parseInt(conData.get("ID")),
                    conData.get("NAME"),
                    Integer.parseInt(conData.get("VALUE"))
            ));
        }
        return discountList;
    }
}
