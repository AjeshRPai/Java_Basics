package RxJavaExamples.Observables;

import io.reactivex.rxjava3.annotations.*;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.*;
import io.reactivex.rxjava3.observers.*;
import io.reactivex.rxjava3.schedulers.*;

public class SingleExample {
    public static void main(String[] args) throws InterruptedException {

        Disposable disposable = getSingle().subscribeOn(Schedulers.single()).subscribeWith(new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println("observer got the value  = " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("observer got the exception = " + e);
            }
        });
        Thread.sleep(1000);
        disposable.dispose();
    }

    private static @NonNull Single<String> getSingle() {
        System.out.println("Emitting called");
        return Single.just("Emitting the data");
    }
}
