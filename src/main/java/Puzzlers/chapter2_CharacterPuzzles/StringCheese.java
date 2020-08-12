package Puzzlers.chapter2_CharacterPuzzles;

public class StringCheese {

    public static void main(String[] args) {
        byte[] bytes = new byte[256];
        for (int i = 0; i < 256; i++)
            bytes[i] = (byte) i;
        String str = new String(bytes);
        for (int i = 0, n = str.length(); i < n; i++)
            System.out.print((int) str.charAt(i) + " ");
    }


    /**
     * The culprit here is the String(byte[]) constructor.
     * Its specification says: “Constructs a new String by decoding the specified byte array using the platform’s default charset.
     * The length of the new String is a function of the charset, and hence may not be equal to the length of the byte array.
     * The behavior of this constructor when the given bytes are not valid in the default charset is unspecified”
     *
     *
     * What exactly is a charset? Technically, it is “the combination of a coded char- acter set and a character-encoding scheme”
     *
     * In other words, a charset is a bunch of characters, the numerical codes that represent them,
     * and a way to translate back and forth between a sequence of character codes and a sequence of bytes.
     * The translation scheme differs greatly among charsets.
     * Some have a one-to-one mapping between characters and bytes; most do not.
     *
     *
     * Summary
     * every time you translate a byte sequence to a String, you are using a charset, whether you specify it explicitly or not
     *
     * */

}
