package Collections;

import java.util.*;


// Hash table implements map interface
// Hash table doesnt allow to insert null key or null value
// Hash table is synchronized

public class HashTableExamples {

    public static void main(String[] args) {
        Hashtable<String, Double> balance = new Hashtable<String, Double>();
        Enumeration names;
        String str;
        double bal;

        balance.put("Zara", 3434.34);
        balance.put("Mahnaz", 123.22);
        balance.put("Ayan", 1378.00);
        balance.put("Daisy", 99.22);
        balance.put("Qadir", -19.08);

        // Show all balances in hash table.
        names = balance.keys();

        while (names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + ": " + balance.get(str));
        }
        System.out.println();

        // Deposit 1,000 into Zara's account
        bal = ((Double) balance.get("Zara")).doubleValue();
        balance.put("Zara", new Double(bal + 1000));
        System.out.println("Zara's new balance: " + balance.get("Zara"));
    }
}
