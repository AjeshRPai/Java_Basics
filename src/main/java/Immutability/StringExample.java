package Immutability;

public class StringExample {

    public static void main(String[] args) {
        String first = "Baeldung";
        String second = "Baeldung";
        System.out.println(first == second);

        String third = new String("Baeldung");
        String fourth = new String("Baeldung");
        System.out.println(third == fourth); // False

        String fifth = "Baeldung";
        String sixth = new String("Baeldung");
        System.out.println(fifth == sixth);
    }
}
