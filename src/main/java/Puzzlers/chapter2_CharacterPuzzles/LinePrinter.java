package Puzzlers.chapter2_CharacterPuzzles;

public class LinePrinter {
    public static void main(String[] args) {
        //// Note:  is Unicode representation of linefeed (LF) //
        char c = 0x000A;
        System.out.println(c);
    }


    /**
     * This program contains a single Unicode escape (\u000A), located in its sole
     * comment. As the comment tells you, this escape represents the linefeed character, and the compiler duly translates it before discarding the comment.
     * Unfortunately, this linefeed character is the first line terminator after the two slash characters that begin the comment (//) and so terminates the comment [JLS 3.4].
     * The words following the escape (is Unicode representation of linefeed (LF)) are therefore not part of the comment; nor are they syntactically valid.
     *
     *
     *
     * To make this more concrete, here is what the program looks like after the Uni- code escape has been translated into the character it represents:
     *    public class LinePrinter {
     *        public static void main(String[] args) {
     *           // Note:
     *     is Unicode representation of linefeed (LF)
     *            char c = 0x000A;
     *      System.out.println(c); }
     * }
     *
     *
     *
     * Solution
     *
     *  public class LinePrinter {
     *        public static void main(String[] args) {
     * char c = ’\n’;
     * System.out.println(c); }
     * }
     * Once this has been done, the program will compile and run, but it’s still a questionable program.
     * It is platform dependent for exactly the reason suggested in the puzzle.
     * On certain platforms, such as UNIX, it will print two complete line separators; on others, such as Windows, it won’t.
     *
     *
     *
     * */
}
