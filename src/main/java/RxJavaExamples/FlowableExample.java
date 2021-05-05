package RxJavaExamples;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.subscribers.*;

public class FlowableExample {

    public static void main(String[] args) {
        Flowable.range(1, 100).subscribe(new DisposableSubscriber<Integer>() {
            @Override
            public void onStart() {
                request(1);
            }

            public void onNext(Integer v) {
                compute(v);
                request(1);
            }

            @Override
            public void onError(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        });
    }

    private static void compute(Integer v) {
        System.out.println("v = " + v);
        ;
    }

}
