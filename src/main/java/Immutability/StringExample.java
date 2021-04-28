package Immutability;

public class StringExample {

    public static void main(String[] args) {
        String inStringPool = "String";
        String inHeapMemory = new String("String");

        System.out.println(inStringPool == inHeapMemory.intern());


    }

}
