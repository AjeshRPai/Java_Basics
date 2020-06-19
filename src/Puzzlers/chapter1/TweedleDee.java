package Puzzlers.chapter1;

public class TweedleDee {

    public static void main(String[] args) {

        Object x = "Buy ";
        String i = "Effective Java!";
        //The simple assignment is legal because x + i is of type String, and String is assignment compatible with Object:
        x = x + i;
        //The compound assignment is illegal because the left-hand side has an object refer-ence type other than String
        x += i;

    }

    /**
     *
     * Compound assignment operators require both operands to be primitives, such as int, or boxed primitives,such as Integer,
     * with one exception: The += operator allows its right-hand operand to be of any type if the variable on the left-hand side is of type String,
     * in which case the operator performs string concatenation [JLS 15.26.2].
     * The simple assignment operator (=) is much less picky when it comes to allowing object reference types on the left-hand side:
     * You can use them to your heart’s content so long as the expression on the right-hand side is assignment
     * compatible with the variable on the left
     * */
}
