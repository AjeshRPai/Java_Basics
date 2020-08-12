# Streams

Streams are not a data structure or Collection. It doesn't hold any data, it's an abstraction. 

---

## Why use Streams

- They are more declarative and expressive. Compared to the Loop , The loop logic is always buried in the loop

   

```java
List<Person> filtered = new ArrayList<>();
 for(Person p : people) {
     if(p.age() < 19) {
         filtered.add(p);
     }
 }
 return filtered;
```

 

```java
return people
     .filter( p -> p.age() < 19)
     .collect(toList());
```

- Streams have a strong affinity with functions. Java 8 introduces lambdas and functional interfaces, which opens a whole
amount of powerful techniques. Streams provide the most convenient and natural way to apply functions to sequences of objects.
- Streams encourage less mutability. This is sort of
related to the functional programming aspect -- the kind of programs you write using streams tend to be the kind of programs where you don't modify objects.
- Streams encourage looser coupling.Your stream-handling code doesn't need to know the source of the stream, or its eventual terminating method.
- **Streams provide scope for future efficiency gains**. Some people have benchmarked and found that single-threaded streams from in-memory `List`s or arrays can be slower than the equivalent loop. This is plausible because there are more objects and overheads in play.But streams scale. As well as Java's built-in support for parallel stream operations

---

## **Operations**

Java8 Streams have two types of operations, known as

### **Intermediate operations**

Intermediate operations just create another stream, but won't perform any processing until the terminal operation is called

1.**Stateful Intermediate Operations**

These intermediate operations need to store the state, and hence can cause bad performance of your applications,
e.g. distinct(), sort(), limit(), etc.

2.**Stateless Intermediate Operations**

These intermediate operations can be
processed independently as they don't need to remember the state, e.g.
filter(), map(), etc.

### **Terminal operations**

Terminal operations when called traversal of streams begins and the associated function is applied one by one. Intermediate operations are lazy operations, so Streams supports laziness. 

---

### **Short circuit behaviour**

Short-circuiting will terminate the processing once condition met. There are a number of short-circuiting operations available. For e.g. anyMatch, allMatch, findFirst, findAny, limit, etc.

### References

[Streams%20e88d3e244ee14a29a05305c99ca7987f/java-8-streams-cheat-sheet.pdf](Streams%20e88d3e244ee14a29a05305c99ca7987f/java-8-streams-cheat-sheet.pdf)

[Performance With Java8 Streams - DZone Performance](https://dzone.com/articles/performance-with-java8-streams)

[Java Streams: The Real Powerhouse in Java 8 | JRebel by Perforce](https://www.jrebel.com/blog/java-streams-vjug-venkat-subramaniam)

[In Java, what are the advantages of streams over loops?](https://stackoverflow.com/questions/44180101/in-java-what-are-the-advantages-of-streams-over-loops)

[The Java 8 Stream API Tutorial | Baeldung](https://www.baeldung.com/java-8-streams)

[https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#NonInterference](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#NonInterference)