package Puzzlers.chapter1;

public class Multicast {

    public static void main(String[] args) {
        System.out.println((int) (char) (byte) -1);
    }

    /**
     * Sign extension is performed if the type of the original value is signed;
     * zero extension if it is a char, regardless of the type to which it is being converted.
     *
     * Because byte is a signed type, sign extension occurs when converting the byte value 1 to a char.
     * The resulting char value has all 16 bits set, so it is equal to 216  1,
     * or 65,535. The cast from char to int is also a widening primitive conversion,
     * so the rule tells us that zero extension is performed rather than sign extension. The resulting int value is 65535,
     *
     * */
}


