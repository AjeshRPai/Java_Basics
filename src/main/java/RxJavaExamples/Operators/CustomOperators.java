package RxJavaExamples.Operators;

import io.reactivex.rxjava3.annotations.*;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.*;

import java.util.*;

public class CustomOperators {
    public static void main(String[] args) {
        List<String> results = new ArrayList<>();
        Observable.just("APPLE").compose(toLowerCase()).subscribe(s -> System.out.println("results = " + s));

    }

    public static ToCleanString toLowerCase() {
        return new ToCleanString();
    }

    static class ToCleanString implements ObservableTransformer<String, String> {

        public ToCleanString() {
            super();
        }

        public static ToCleanString toLowerCase() {
            return new ToCleanString();
        }

        @Override
        public @NonNull ObservableSource<String> apply(@NonNull Observable<String> upstream) {
            return upstream.map(String::toLowerCase);
        }
    }

}
