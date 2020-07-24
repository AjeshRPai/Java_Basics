package Immutability;

public class PrimitiveTypeExample {

    //Primitive data types are Immutable while non primitive data types
    // like objects and arrays are mutable

    public static void main(String[] args) {
        Integer test = 10;
        increment(test);
        System.out.println("test = " + test);

        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        change(cars);
        System.out.println("cars[0] = " + cars[0]);

        Something mutable = new Something();
        increment2(mutable);
        System.out.println("mutable = " + mutable);

    }

    private static void increment2(Something something) {
        something.someField = 50;
    }

    private static void increment(Integer test) {
        test = test + 5;
    }

    private static void change(String[] cars) {
        cars[0] = "Mercedes";
    }

    static class Something {
        int someField = 10;

        @Override
        public String toString() {
            return "Something{" + "someField=" + someField + '}';
        }
    }
}
