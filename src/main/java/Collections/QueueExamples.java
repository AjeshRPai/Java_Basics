package Collections;


// https://medium.com/javarevisited/priority-queues-in-java-2b6da7463998

public class QueueExamples {

    public static void main(String[] args) {
//        final PriorityQueue<Application> pq = IntStream.rangeClosed(0, r.nextInt(10)).mapToObj(i -> new Application(Integer.toString(i))).collect(PriorityQueue::new, PriorityQueue::offer, AbstractQueue::addAll);
//        Stream.generate(() -> Optional.ofNullable(pq.poll())).sequential().takeWhile(Optional::isPresent).map(Optional::get).forEach(s -> {
//            s.block();
//            if (s.hasMoreWork()) {
//                pq.offer(s);
//            }
//        });
    }

    public static class Application implements Comparable<Application> {
        private final String name;
        private int priority;
        private int todo;

        public Application(String name) {
            this.name = name;
            this.priority = 0;
//            this.todo = r.nextInt(100);
        }

        public boolean hasMoreWork() {
            return todo > 0;
        }

        public void block() {
            System.out.println("priority was " + priority);
            priority = 0;
//            IntStream.range(0, r.nextInt(5)).forEach(i -> doWork());
        }

        private void doWork() {
            System.out.println("doing work for " + this);
            todo--;
            priority++;
            try {
                Thread.sleep(100); // hard at work!
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int compareTo(Application o) {
            return priority - o.priority;
        }

        public String toString() {
            return "Application " + name;
        }
    }

}
