package Puzzlers.chapter1;

public class DosEquis {

    public static void main(String[] args) {
        char x = 'X';
        int i = 0;
        System.out.print(true ? x : 0);
        System.out.print(false ? i : x);

    }

    /***
     * The rules for determining the result type of a conditional expression are too long and complex to reproduce in their entirety,
     * but here are three key points.
     * 1. If the second and third operands have the same type, that is the type of the conditional expression.
     * In other words, you can avoid the whole mess by steering clear of mixed-type computation.
     * 2. If one of the operands is of type T where T is byte, short,
     * or char and the other operand is a constant expression of type int whose value is representable in type T,
     * the type of the conditional expression is T.
     * 3. Otherwise, binary numeric promotion is applied to the operand types,
     * and the type of the conditional expression is the promoted type of the second and third operands.
     *
     *
     * Therefore, point 2 applies to
     * the first expression and its return type is char. Point 3 applies to the second condi- tional expression,
     * and its return type is the result of applying binary numeric pro- motion to int and char, which is int
     *
     * For the first expression, PrintStream.print(char) is invoked; for the second, PrintStream.print(int)
     *
     * */

}
