package Puzzlers.chapter1;

public class JoyOfHex {
    public static void main(String[] args) {
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

        //Solution

        System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
    }


    /**
     * negative decimal constants are clearly identifiable by the presence of a minus sign.
     * Not so for hexadecimal and octal literals.
     * They can take on both positive and negative values.
     * Hex and octal literals are negative if their high-order bit is set.
     *
     *
     *The lesson of this puzzle is that mixed-type computations can be confusing,
     *  more so given that hex and octal literals can take on negative values without an explicit minus sign.
     *
     *
     *
     * */
}


