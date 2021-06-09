package RxJavaExamples.Subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class BehaviourSubjectExample {
    
    public static void main(String[] args) {
    
        Observer<String> observer = getObserver();
    
        // observer will receive no onNext events
        // because the subject.onComplete() isn't called.
        // observer will receive all 4 events (including "default").
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("default");
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
    
        // observer will receive the "one", "two" and "three" events, but not "default" and "zero"
        BehaviorSubject<String> subject2 = BehaviorSubject.createDefault("default");
        subject2.onNext("zero");
        subject2.onNext("one");
        subject2.subscribe(observer);
        subject2.onNext("two");
        subject2.onNext("three");
    
        // observer will receive only onCompleted
        BehaviorSubject<String> subject3 = BehaviorSubject.createDefault("default");
        subject3.onNext("zero");
        subject3.onNext("one");
        subject3.onComplete();
        subject3.subscribe(observer);
    
        // observer will receive only onError
        BehaviorSubject<String> subject4 = BehaviorSubject.createDefault("default");
        subject4.onNext("zero");
        subject4.onNext("one");
        subject4.onError(new RuntimeException("error"));
        subject4.subscribe(observer);
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
