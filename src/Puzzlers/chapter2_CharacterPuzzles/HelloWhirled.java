package Puzzlers.chapter2_CharacterPuzzles;


/**
 * Generated by the IBM IDL-to-Java compiler, version 1.0
 * from F:\TestRoot\apps\a1 units\include\PolicyHome.idl
 * Wednesday, June 17, 1998 6:44:40 o’clock AM GMT+00:00
 */


public class HelloWhirled {
    public static void main(String[] args) {
        System.out.print("Hell");
        System.out.println("o world");
    }
}


/**
 * The problem is in the third line of the comment, which contains the characters \ -units.
 * These characters begin with a backslash (\) followed by the letter u, which denotes the start of a Unicode escape.
 * Unfortunately, these characters are not followed by four hexadecimal digits, so the Unicode escape is ill-formed, and the compiler is required to reject the program.
 * Unicode escapes must be well formed, even if they appear in comments.
 * <p>
 * <p>
 * <p>
 * SUMMARY
 * <p>
 * In summary, ensure that the characters \  u do not occur outside the context of a valid Unicode escape,
 * even in comments. Be particularly wary of this problem in machine-generated code.
 */