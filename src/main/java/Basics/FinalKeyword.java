package Basics;

//https://www.baeldung.com/java-final

public class FinalKeyword {

    public static final String staticKey;

    static {
        staticKey = "Final Value";
    }

    public final String nonStaticKey;

    {
        nonStaticKey = "finalValue";
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setName("Initial name");
        dog.setName("can be changed");

        // final variables cant be reassigned
        final String constantVariable = "this is a constant";
        // this will throw a compilation error
        // since it cant be reassigned
        // constantVariable = "some other value";


        // final reference variables mean they cant be
        // reassigned
        // but this doesnt mean that the object
        // itself is immutable
        final Dog newDog = new Dog();
        newDog.setName("Any Name");
        newDog.setName("Can Change the Name");

        // compiler error
        // newDog = dog;

        // Final Fields


    }

    public void methodWithFinalArguments(final int x) {
        // cannot change the value
        // x=1;
    }
}


// Final fields

// classess marked as final cannot be extended
// this doesnt mean that the objects are immutable
final class Dog {
    private int weight;
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
// compilation error since Dog is final
//class Newbreed extends Dog {
//
//}


// Methods marked as final cannot be overriden
// If u want the extensibility for classess but
// want the Methods not to be overriden then
// final keyword can be used
class Cat {
    public final String makeSound() {
        return "sound";
    }
}

class BlackCat extends Cat {
//    Cannot override since the method is final
//    @Override
//    public final String makeSound() {
//
//    }
}