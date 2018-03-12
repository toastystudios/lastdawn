/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Toasty Studios
 */
public class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Rounds a number down e.g 0.5 equals 0 and 1.6 equals 2
     *
     * @param num
     * @return
     */
    public static double roundDown(double num) {
        BigDecimal bigDecimal = BigDecimal.valueOf(num);
        return bigDecimal.setScale(0, RoundingMode.HALF_DOWN).doubleValue();
    }

}
