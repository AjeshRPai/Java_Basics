package RxJavaExamples;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.*;
import io.reactivex.rxjava3.observers.*;
import io.reactivex.rxjava3.schedulers.*;

import java.util.concurrent.*;

public class UndeliverableException {

    public static void main(String[] args) throws InterruptedException {
        Completable completable1 = Completable.error(new Throwable("error 1")).subscribeOn(Schedulers.io());

        Completable completable2 = Completable.error(new Throwable("error 2")).subscribeOn(Schedulers.io());

        Disposable d = completable1.mergeWith(completable2).delay(10, TimeUnit.SECONDS, Schedulers.io()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onStart() {
                System.out.println("Started");
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        });

        Thread.sleep(5000);

        d.dispose();

    }
}
