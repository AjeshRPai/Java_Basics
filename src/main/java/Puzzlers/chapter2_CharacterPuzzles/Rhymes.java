package Puzzlers.chapter2_CharacterPuzzles;

import java.util.*;

public class Rhymes {
    private static Random rnd = new Random();

    public static void main(String[] args) {


        StringBuffer word = null;
        switch (rnd.nextInt(2)) {
            case 1:
                word = new StringBuffer('P');
            case 2:
                word = new StringBuffer('G');
            default:
                word = new StringBuffer('M');
        }

        word.append('a');
        word.append('i');
        word.append('n');
        System.out.println(word);
    }


    /**
     * The first bug is that the random number is chosen so the switch statement can reach only two of its three cases.
     * The specification for Random.nextInt(int) says:
     * “Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)” [Java-API].
     * This means that the only possible values of the expression rnd.nextInt(2) are 0 and 1.
     * The switch statement will never branch to case 2,
     * which suggests that the program will never print Gain. The parameter to nextInt should have been 3 rather than 2.
     *
     *
     * there are no break statements between the cases.
     * Whatever the value of the switch expression, the program will execute that case and all subsequent cases [JLS 14.11].
     * Each case assigns a value to the variable word, and the last assignment wins.
     * The last assignment will always be the one in the final case (default), which is new StringBuffer(’M’).
     * This suggests that the program will never print Pain or Gain but always Main.
     *
     *
     * */
}
