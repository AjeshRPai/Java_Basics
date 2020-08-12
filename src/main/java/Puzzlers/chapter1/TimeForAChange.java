package Puzzlers.chapter1;

import java.math.*;

public class TimeForAChange {

    /**
     * Tom goes to the auto parts store to buy a spark plug that costs $1.10,
     * but all he has in his wallet are two-dollar bills.
     * How much change should he get if he pays for the spark plug with a two-dollar bill?
     */

    public static void main(String args[]) {
        System.out.println(2.00 - 1.10);

        // Poor solution - still uses binary floating-point!
        System.out.printf("%.2f%n", 2.00 - 1.10);

        // Always use the BigDecimal(String) constructor, never BigDecimal(double).

        System.out.println(new BigDecimal(2).
                subtract(new BigDecimal(1.10)));

        System.out.println(new BigDecimal("2.00").
                subtract(new BigDecimal("1.10")));
    }

    /**
     *
     * The problem is that not all decimals can be represented exactly using binary floating-point.
     *
     * Binary floating-point is particularly ill-suited to monetary calculations,
     * as it is impossible to represent 0.1—or any other negative power of 10—exactly as a finite-length binary fraction
     *
     * In summary, avoid float and double where exact answers are required; for monetary calculations, use int, long, or BigDecimal.
     * */
}
