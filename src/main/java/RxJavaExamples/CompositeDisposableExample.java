package RxJavaExamples;


import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.*;
import io.reactivex.rxjava3.observers.*;
import io.reactivex.rxjava3.schedulers.*;

import java.util.concurrent.*;

public class CompositeDisposableExample {
    public static void main(String[] args) throws InterruptedException {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        //Create an Single observer
        Disposable disposableSingle = Single.just("Hello World").delay(2, TimeUnit.SECONDS, Schedulers.io()).subscribeWith(new DisposableSingleObserver<String>() {
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(String value) {
                System.out.println(value);
            }
        });

        //Create an observer
        Disposable disposableMayBe = Maybe.just("Hi").delay(2, TimeUnit.SECONDS, Schedulers.io()).subscribeWith(new DisposableMaybeObserver<String>() {
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(String value) {
                System.out.println(value);
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        });

        Thread.sleep(3000);

        compositeDisposable.add(disposableSingle);
        compositeDisposable.add(disposableMayBe);

        //start observing
        compositeDisposable.dispose();
    }
}