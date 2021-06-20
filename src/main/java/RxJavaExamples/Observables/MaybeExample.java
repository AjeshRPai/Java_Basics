package RxJavaExamples.Observables;

import io.reactivex.rxjava3.annotations.*;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.*;
import io.reactivex.rxjava3.observers.*;
import io.reactivex.rxjava3.schedulers.*;

public class MaybeExample {
    public static void main(String[] args) throws InterruptedException {

        Disposable disposable = getMaybe().subscribeOn(Schedulers.computation()).subscribeWith(new DisposableMaybeObserver<String>() {
            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println("observer got the value on Success  = " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("exception to string = " + e);

            }

            @Override
            public void onComplete() {
                System.out.println("May be on Complete ");
            }
        });

        Thread.sleep(1000);
        disposable.dispose();
    }

    private static @NonNull Maybe<String> getMaybe() {
        System.out.println("Emitting called");
//        return Maybe.just("Emitting the data");
        return Maybe.empty();
    }

}
