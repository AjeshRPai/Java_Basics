package Puzzlers.chapter2_CharacterPuzzles;

public class EscapeRout {

    public static void main(String[] args) {
        // \u0022 is the Unicode escape for double quote (")
        System.out.println("a\u0022.length() + \u0022b".length());
    }


    /**
     * Java provides no special treat- ment for Unicode escapes within string literals.
     * The compiler translates Uni- code escapes into the characters they represent before it parses the program into tokens,
     * such as strings literals [JLS 3.2].
     * Therefore, the first Unicode escape in the program closes a one-character string literal ("a"),
     * and the second one opens a one-character string literal ("b").
     *
     * The program prints the value of the expression "a".length() + "b".length(), or 2.
     *
     *
     * ASCII is the lowest common denominator of character sets.
     * ASCII has only 128 characters, but Unicode has more than 65,000.
     * A Unicode escape can be used to insert any Unicode character into a program using only ASCII characters.
     *
     *  prefer escape sequences to Unicode escapes in string and character literals.
     *  Unicode escapes can be confusing because they are processed so early in the compilation sequence.
     *  Do not use Unicode escapes to represent ASCII characters.
     * */
}
