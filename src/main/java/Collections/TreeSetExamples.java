package Collections;

import java.util.*;

//Both the Classess Implement Navigable set and Sorted Set which gives access to methods like
// Floor
// Higher
// Lower
@SuppressWarnings("ALL")
public class TreeSetExamples {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(4);
        set.add(3);
        set.add(5);

        set.lower(5);
        set.floor(4);

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(2, 4);
        tm.put(3, 5);
        tm.put(4, 5);
        tm.put(2, 3);
        System.out.println(set);
        System.out.println(tm);
    }
}
