package Puzzlers.chapter2_CharacterPuzzles;

public class AnimalFarm {
    public static void main(String[] args) {

        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out.println("Animals are equal: " + pig == dog);

        final String dog2 = "length: 10";
        //Constant expression example
        System.out.println("Animals are equal: " + (pig == dog2));

    }


    /**
     * compile-time constants of type String are interned
     *  In other words, any two constant expressions of type String
     *  that designate the same character sequence are represented by identical object references
     *
     *
     *  If initialized with constant expressions, both pig and dog would indeed refer to the same object,
     *  but dog is not initialized with a constant expression.
     *  The language constrains which operations are permitted to appear in a constant expression
     *  and method invocation is not among them
     *
     *
     *  The + operator, whether used for addition or string concatenation, binds more tightly than the == operator.
     *  System.out.println(("Animals are equal: " + pig) == dog);
     *
     * When using the string concatenation operator, always parenthesize nontrivial operands.
     *
     * */
}
