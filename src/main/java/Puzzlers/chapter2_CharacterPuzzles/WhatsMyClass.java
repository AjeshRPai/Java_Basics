package Puzzlers.chapter2_CharacterPuzzles;

import java.io.*;

public class WhatsMyClass {
    public static void main(String[] args) {
        System.out.println(WhatsMyClass.class.getName().replaceAll(".", "/") + ".class");


        // Solution
        // If, however,
        // the file separator is a backslash, as it is on Windows, the program prints something like this:

        System.out.println(WhatsMyClass.class.getName().replaceAll("\\.", "/") + ".class");


        // Platform independent solution

        System.out.println(WhatsMyClass.class.getName().replace('.', File.separatorChar) + ".class");

    }


    /**
     * The problem is that String.replaceAll takes a regular expression as its first parameter, not a literal sequence of characters.
     * The regular expression "." matches any single character, and so every character of the class name is replaced by a slash,
     * producing the output we saw.
     * */

}
