package Puzzlers.chapter1;

public class Oddity {


    public static void main(String[] args) {
        // Does the method work
        System.out.println("puzzle " + isOdd(-1));
        checkOperand();
    }
    //	Notes:
    //	Think about the Operand(input values) - before using the operator
    //  Operand - Int can be zero, negative or positive
    //	How does the operator behave depending on the those values
    //  Operator gives the same sign as operand


    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    public static void checkOperand() {
        int negativeNumber1 = -20;
        int negativeNumber2 = -19;

        System.out.println(negativeNumber1 / negativeNumber2);

        System.out.println(negativeNumber1 % negativeNumber2);

        System.out.println(negativeNumber1++);

        System.out.println(negativeNumber1--);


    }

    /**
     * when the remainder operation returns a nonzero result,
     * it has the same sign as its left operand.
     * Solution
     */

    public static boolean isOddSolution(int i) {
        return i % 2 != 0;
    }

    /**
     * iff you are using the isOdd method in a performance-critical setting,
     * you would be better off using the bitwise AND operator (&) in place of the remainder operator:
     * <p>
     * <p>
     * As a general rule, the divide and remainder operations are slow compared to other arithmetic and logical operations
     */

    public static boolean isOddBitwise(int i) {
        return (i & 1) != 0;
    }

    /**Summary
     *
     * Think about the signs of the operands and of the result whenever you use the remainder operator.
     *
     * */

}
