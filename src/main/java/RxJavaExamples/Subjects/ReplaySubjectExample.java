package RxJavaExamples.Subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class ReplaySubjectExample {
    
    public static void main(String[] args) {
    
        Observer<String> observer = getObserver();
        Observer<String> observer2 = getObserver();
    
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.onComplete();
    
        // both of the following will get the onNext/onCompleted calls from above
        subject.subscribe(observer);
        subject.subscribe(observer2);
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
