package RxJavaExamples.Subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.UnicastSubject;

public class UnicastSubjectExample {
    
    public static void main(String[] args) {
    
        Observer<String> observer = getObserver();
    
        // observer will receive no onNext events
        // because the subject.onComplete() isn't called.
        UnicastSubject<String> subject = UnicastSubject.create();
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.subscribe(observer);
    
        // observer will receive "three" as the only onNext event.
        UnicastSubject<String> subject2 = UnicastSubject.create();
        subject2.subscribe(observer);
        subject2.onNext("one");
        subject2.onNext("two");
        subject2.onNext("three");
        subject2.onComplete();
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
