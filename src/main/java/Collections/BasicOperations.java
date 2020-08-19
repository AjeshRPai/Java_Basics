package Collections;

import java.util.*;

@SuppressWarnings("ALL")
public class BasicOperations {
    public static void main(String[] args) {
        System.out.println("Adding Elements");
        //Adding Elements
        // 1. Using add
        Collection<String> todoMonday = new ArrayList<>();
        todoMonday.add("Mon1");
        todoMonday.add("Mon2");
        todoMonday.add("Mon3");
        todoMonday.add("Mon4");
        todoMonday.add("Mon5");
        todoMonday.add("Mon6");
        System.out.println("todoMonday = " + todoMonday);

        //2.Passing collection on Initialization
        Collection<String> todoWeek = new ArrayList<>(todoMonday);
        System.out.println("todoWeek = " + todoWeek);

        //3.Using Collections addAll
        Collection<String> todoTuesday = new ArrayList<>();
        todoTuesday.add("tue1");
        todoWeek.addAll(todoTuesday);
        System.out.println("todoWeek = " + todoWeek);

        System.out.println("Querying the Contents");
        //1.
        queryingTheContent(todoWeek);
        queryingTheContentUsingIterator(todoWeek);
        queryingTheContentIteratorModification(todoWeek);

        System.out.println("Removing elements");
        //Removing Elements
        //1.
        boolean wasPresent = todoWeek.remove("Mon1");
        System.out.println("wasPresent = " + wasPresent);

        //2.Retains the elements passed and removes the rest
        todoWeek.retainAll(todoMonday);
        System.out.println("retain all todo monday " + "todoWeek = " + todoWeek);
        // This will retain all the elements except Mon1
        // which is already deleted so wont happen anything in that case

        //3.Removes all elements passed on the list
        boolean collectionRemoved = todoMonday.removeAll(todoTuesday);
        System.out.println("collectionRemoved = " + collectionRemoved);
        //This will print false as the collection was not present

        //4.clear the Elements
        todoWeek.clear();
        System.out.println("todoWeek = " + todoWeek);


    }

    public static void queryingTheContent(Collection<String> collection) {
        try {
            for (String t : collection) {
                if (t instanceof String) {
                    collection.remove(t);
                }
            }
        } catch (ConcurrentModificationException exception) {
            System.out.println("queryingTheContent throwed Concurrent Exception");
        }
    }

    public static void queryingTheContentUsingIterator(Collection<String> collection) {
        try {
            for (Iterator<String> it = collection.iterator(); it.hasNext(); ) {
                String next = it.next();
                if (next instanceof String) {
                    collection.remove(next);
                }
            }

        } catch (ConcurrentModificationException exception) {
            System.out.println("queryingTheContentUsingIterator throwed Concurrent Exception");
        }

    }

    public static void queryingTheContentIteratorModification(Collection<String> collection) {
        try {
            for (Iterator<String> iterator = collection.iterator(); iterator.hasNext(); ) {
                String nextOne = iterator.next();
                if (nextOne instanceof String) {
                    iterator.remove();
                }
            }
        } catch (ConcurrentModificationException exception) {
            System.out.println("queryingTheContentIteratorModification throwed Concurrent Exception");
        }
    }


}
