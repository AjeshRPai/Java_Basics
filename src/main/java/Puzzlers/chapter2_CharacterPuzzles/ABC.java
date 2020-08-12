package Puzzlers.chapter2_CharacterPuzzles;

public class ABC {

    public static void main(String[] args) {
        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        //Problem
        System.out.println(letters + " easy as " + numbers);

        //Solution
        System.out.println(letters + " easy as " + String.valueOf(numbers));
    }


    /**
     *
     * If the reference is null, it is converted to the string "null".
     * Otherwise, the con- version is performed as if by an invocation of the toString method of the referenced object with no arguments;
     * but if the result of invoking the toString method is null, then the string "null" is used instead
     *
     * Arrays inherit the toString method from Object [JLS 10.7], whose specification says, “Returns a string consisting of the name of the class of which the object is an instance, the at-sign character ’@’,
     * and the unsigned hexadecimal representation of the hash code of the object” [Java-API].
     * The specification for Class.getName says that the result of invoking this method on the class object for char[] is the string "[C".
     * Putting it all together gives the ugly string printed by our program
     *
     *
     *
     To summarize, char arrays are not strings. To convert a char array to a string, invoke String.valueOf(char[]).
     * */
}
