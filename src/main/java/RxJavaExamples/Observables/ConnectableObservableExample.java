package RxJavaExamples.Observables;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.*;
import io.reactivex.rxjava3.observables.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.*;

public class ConnectableObservableExample {

    public static void main(String[] args) {

        LOGGER.info("Subscribing");

        Observable obs = getObservable();

        Disposable s1 = obs.subscribe(i -> LOGGER.info("subscriber#1 is printing " + i));
        Disposable s2 = obs.subscribe(i -> LOGGER.info("subscriber#2 is printing " + i));


        ConnectableObservable connectableObservable = getConnectedObservable();

        Disposable d1 = connectableObservable.subscribe(i -> LOGGER.info("subscriber#1 is printing " + i));
        Disposable d2 = connectableObservable.subscribe(i -> LOGGER.info("subscriber#2 is printing " + i));

        connectableObservable.connect();


    }

    private static Observable getObservable() {
        return Observable.create(subscriber -> {
            subscriber.onNext(gettingValue(1));
            subscriber.onNext(gettingValue(2));
        });
    }

    private static ConnectableObservable getConnectedObservable() {
        ConnectableObservable obs = Observable.create(subscriber -> {
            subscriber.onNext(gettingValue(1));
            subscriber.onNext(gettingValue(2));
        }).publish();
        return obs;
    }

    ;

    private static Integer gettingValue(int i) {
        LOGGER.info("Getting " + i);
        return i;
    }
}
