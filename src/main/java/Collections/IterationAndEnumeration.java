package Collections;

import java.util.*;

public class IterationAndEnumeration {

    public static void main(String args[]) {
        List list = new ArrayList(Arrays.asList(new String[]{"Apple", "Cat", "Dog", "Rat"}));
        Vector v = new Vector(list);
        deleteThroughEnumeration(v, "Dog");
    }

    private static void deleteThroughEnumeration(Vector v, String name) {
        Enumeration e = v.elements();
        while (e.hasMoreElements()) {
            String s = (String) e.nextElement();
            if (s.equals(name)) {
                v.remove(name);
            }
        }
        // Display the names
        System.out.println("The names are:");
        e = v.elements();
        while (e.hasMoreElements()) {
            // Prints elements
            System.out.println(e.nextElement());
        }
    }

    private static void deleteThroughIteration(Vector v, String name) {
        Iterator i = v.iterator();
        while (i.hasNext()) {
            String s = (String) i.next();
            if (s.equals(name)) {
                i.remove();
            }
        }
        // Display the names
        System.out.println("The names are:");
        i = v.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
