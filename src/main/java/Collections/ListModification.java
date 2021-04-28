package Collections;

import java.util.*;

public class ListModification {

    public static void main(String[] args) {

        List<Something> somethings = new ArrayList<>();

        Something something = new Something(1, "1");
        Something something2 = new Something(2, "2");
        Something something3 = new Something(3, "3");
        Something something4 = new Something(4, "4");

        somethings.add(something);
        somethings.add(something2);
        somethings.add(something3);
        somethings.add(something4);

        Something lastItem = new Something();
        lastItem = somethings.get(0);
        lastItem.value = 10;
        lastItem.stringValue = "10";

        somethings.add(lastItem);
        System.out.println("somethings = " + somethings);

    }

    static class Something {
        private int value;
        private String stringValue;

        public Something(int value, String stringValue) {
            this.value = value;
            this.stringValue = stringValue;
        }

        public Something() {
        }

        @Override
        public String toString() {
            return "Something{" + "value=" + value + ", stringValue='" + stringValue + '\'' + '}';
        }
    }

}
