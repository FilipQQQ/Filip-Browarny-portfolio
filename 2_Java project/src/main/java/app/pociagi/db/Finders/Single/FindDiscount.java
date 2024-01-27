package app.pociagi.db.Finders.Single;

import app.pociagi.db.Objects.Discount;

import java.sql.SQLException;

/** <h> FindDiscount </h>
 * FindDiscount allows to find Discount by unique values
 * @author rafal
 * @since 2022-12-08
 */
public class FindDiscount {

    /**
     * <h> find by discount ID </h>
     * Finds object in DISCOUNTS table by discount id
     * @param discountId id of discount
     * @return Discount object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Discount findByID(Integer discountId) {
        try {
            String name = Finder.find("DISCOUNTS", "NAME", "ID", discountId);
            Integer value = Integer.parseInt(Finder.find("DISCOUNTS", "VALUE", "ID", discountId));
            return new Discount(discountId, name, value);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }

    /**
     * <h> find by discount name </h>
     * Finds object in DISCOUNTS table by discount id
     * @param name name od discount that is displayed
     * @return Discount object or null if it cannot find the object or if found more than 1
     * @author rafal
     * @since 2022-12-08
     */
    public static Discount findByName(String name) {
        try {
            Integer discountId = Integer.parseInt(Finder.find("DISCOUNTS", "ID", "NAME", name));
            Integer value = Integer.parseInt(Finder.find("DISCOUNTS", "VALUE", "NAME", name));
            return new Discount(discountId, name, value);
        } catch (SQLException s) {
            System.err.format("SQL State: %s\n%s", s.getSQLState(), s.getMessage());
            return null;
        }
    }
}