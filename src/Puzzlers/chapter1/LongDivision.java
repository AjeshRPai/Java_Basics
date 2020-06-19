package Puzzlers.chapter1;

public class LongDivision {

    public static void main(String[] args) {
        problem();
        solution();

    }


    /**
     * It seems, then, that the program must print 1000. Unfortunately, it prints 5
     */

    public static void problem() {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }

    /**
     * The problem is that the computation of the constant MICROS_PER_DAY does overflow.
     * Although the result of the computation fits in a long with room to spare,
     * it doesn’t fit in an int.
     * The computation is performed entirely in int arithmetic,
     * and only after the computation completes is the result promoted to a long.
     * By then, it’s too late: The computation has already overflowed, returning a value that is too low by a factor of 200
     * <p>
     * So why is the computation performed in int arithmetic? Because all the factors that are multiplied together are int values.
     * When you multiply two int values,
     * you get another int value. Java does not have target typing,
     * a language feature wherein the type of the variable in which a result is to be stored influences the type of the computation.
     */


    public static void solution() {
        final long MICROS_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }


    /**
     * It’s easy to fix the program by using a long literal in place of an int as the first factor in each product.
     * This forces all subsequent computations in the expres- sion to be done with long arithmetic.
     * Although it is necessary to do this only in the expression for MICROS_PER_DAY, it is good form to do it in both products.
     * Similarly, it isn’t always necessary to use a long as the first value in a product, but it is
     * */


    /**Summary
     *
     *
     * When working with large numbers, watch out for overflow—it’s a silent killer.
     * Just because a variable is large enough to hold a result doesn’t mean that the computation leading to the result is of the correct type.
     * When in doubt, perform the entire computation using long arithmetic.
     *
     * */
}
