# Subject

A Subject is a sort of bridge or proxy that is available in some implementations of ReactiveX that acts both as an
observer and as an Observable. Because it is an observer, it can subscribe to one or more Observables, and because it is
an Observable, it can pass through the items it observes by re-emitting them, and it can also emit new items.

## Publish Subject

> Subject that, once an Observer has subscribed, emits all subsequently observed items to the subscriber.

If a student entered late into the classroom, he just wants to listen from that point of time when he entered the
classroom. So, Publish will be the best for this use-case.

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.PublishSubject.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.PublishSubject.png)

## **Replay Subject**

> It emits all the items of the source Observable, regardless of when the subscriber subscribes.

Here, if a student entered late into the classroom, he wants to listen from the beginning.

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.ReplaySubject.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.ReplaySubject.png)

## **Behavior Subject**

> It emits the most recently emitted item and all the subsequent items of the source Observable when an observer subscribes to it. If the last item was an error then the observer will only get Error. If On Complete gets called before subscribing to the subject then only OnComplete will be received

Here, if a student entered late into the classroom, he wants to listen to the most recent things(not from the beginning)
being taught by the professor so that he gets the idea of the context. So, here we will use `Behavior`.

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.BehaviorSubject.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.BehaviorSubject.png)

## **Async Subject**

> It only emits the last value of the source Observable(and only the last value) only after that source Observable completes. Subject that publishes only the last item observed to each Observer once the source Observable has completed. The item is cached and published to any Observers which subscribe after the source has completed. If the source emitted no items, AsyncSubject completes without emitting anything. If the source terminated in an error, current and future subscribers will receive only the error.

Here, if a student entered at any point in time into the classroom, and he wants to listen only about the last thing(and
only the last thing) being taught after class is over. So, here we will use `Async`.

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.AsyncSubject.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.AsyncSubject.png)

## Serialized Subject

> Wraps a Subject so that it is safe to call its various on methods from different threads.

When you use an ordinary Subject as a Subscriber, you must take care not to call its Observer.onNext(T) method (or its
other on methods) from multiple threads, as this could lead to non-serialized calls, which violates the Observable
contract and creates an ambiguity in the resulting Subject.

## Unicast Subject

A Subject variant which buffers events until a single Subscriber arrives and replays them to it and potentially switches
to direct delivery once the Subscriber caught up and requested an unlimited amount. In this case, the buffered values
are no longer retained. If the Subscriber requests a limited amount, queueing is involved and only those values are
retained which weren't requested by the Subscriber at that time.

![https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/UnicastSubject.v1.png](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/UnicastSubject.v1.png)

[Subject (RxJava Javadoc 1.3.8)](http://reactivex.io/RxJava/javadoc/rx/subjects/Subject.html)

[Understanding RxJava Subject - Publish, Replay, Behavior and Async Subject](https://blog.mindorks.com/understanding-rxjava-subject-publish-replay-behavior-and-async-subject-224d663d452f)