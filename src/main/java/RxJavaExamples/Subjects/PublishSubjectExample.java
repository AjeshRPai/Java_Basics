package RxJavaExamples.Subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class PublishSubjectExample {
    
    public static void main(String[] args) {
    
        Observer<String> observer1 = getObserver();
        Observer<String> observer2 = getObserver();
    
        PublishSubject<String> subject = PublishSubject.create();
        // observer1 will receive all onNext and onCompleted events
        subject.subscribe(observer1);
        subject.onNext("one");
        subject.onNext("two");
        // observer2 will only receive "three" and onCompleted
        subject.subscribe(observer2);
        subject.onNext("three");
        subject.onComplete();
    }
    
    private static Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("SubjectExamples.onSubscribe is called for d");
            }
            
            @Override
            public void onNext(@NonNull String s) {
                System.out.println("on next in observer called s = " + s);
                
            }
            
            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }
            
            @Override
            public void onComplete() {
                System.out.println("On Complete called");
                
            }
        };
    }
    
    
}
