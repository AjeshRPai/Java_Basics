package Puzzlers.chapter1;

public class TweedleDum {

    public static void main(String[] args) {

        short x = 0;
        int i = 123456;

        //this should work
        x += i;
        //The above one contains a hidden cast
        //This should not compile
        //x = x + i;
        //This wont cast automatically and requires an explicit cast
    }

    /**
     * (The compound assignment operators are +=, -=, *=, /=, %=, <<=, >>=, >>>=, &=, ^=, and |=.)
     *
     * E1 = (T) ((E1) op (E2)), where T is the type of E1, except that E1 is evaluated only once
     *
     *
     * To avoid unpleasant surprises, do not use compound assignment operators on variables of type byte, short, or char. When using compound assignment
     * operators on variables of type int, ensure that the expression on the right-hand side is not of type long, float, or double.
     * When using compound assignment operators on variables of type float,
     * ensure that the expression on the right-hand side is not of type double.
     *
     * */
}
