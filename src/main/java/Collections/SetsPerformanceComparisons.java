package Collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 10, time = 1)
public class SetsPerformanceComparisons {

    //Sets doesnt allow duplicate Elements
    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);


    }

    //Hash Set
    // Its implemented by an array.
    // Hashtable in which position is calculated by hash function of contents
    // Order is not maintained
    // Iteration will take O(table size)
    // as the items are stored according to hash set size
    @Benchmark
    public static void hashSetInsertionsWithSize() {
        Set<SomeClass> set = new HashSet<>(1000);
        for (int i = 0; i < 1000; i++) {
            set.add(new SomeClass(i, String.valueOf("value of" + i)));
        }
    }

    @Benchmark
    public static void hashSetInsertionsWithoutSize() {
        Set<SomeClass> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(new SomeClass(i, String.valueOf("value of" + i)));
        }
    }

    @Benchmark
    public static void hashSetInsertions() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }

    //Linked Hashset
    // This class is same as set
    //Order is maintained by having a pointer to the next element
    // Iteration will take O(element numbers)
    // as the items are stored according to hash set size
    @Benchmark
    public static void linkedHashSetInsertions() {
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }


    @Benchmark
    public static void CopyOnWriteArraySetInsertions() {
        Set<Integer> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public static void TreeSetInsertions() {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public static void NavigableSetInsertions() {
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public static void SortedSetInsertions() {
        SortedSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
    }


    private static class SomeClass {
        public Integer someInteger;
        public String someString;

        public SomeClass(Integer someInteger, String someString) {
            this.someInteger = someInteger;
            this.someString = someString;
        }
    }

    public static Set<Integer> iterateSet(Set<Integer> set) {
        for (Integer integer : set) {
            System.out.println("item = " + integer);
        }
        return set;
    }


}
