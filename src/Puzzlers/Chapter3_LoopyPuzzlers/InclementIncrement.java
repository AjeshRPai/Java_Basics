package Puzzlers.Chapter3_LoopyPuzzlers;

public class InclementIncrement {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 100; i++)
            j = j++;
        System.out.println(j);
    }


    /**
     *
     *  The value of the expression j++ is the original value of j before it was incremented.
     *  Therefore, the preceding assignment first saves the value of j,
     *  then sets j to its value plus 1, and,
     *  finally, resets j back to its original value.
     *
     *
     *  Summary
     *  Do not assign to the same variable more than once in a single expression.
     *  An expression containing multiple assignments to the same variable is confusing and seldom does what you want.
     *
     * */
}
