package RxJavaExamples.Operators;


public class createOperators {
    public static void main(String[] args) {

    }

//    private static void createOperator() {
//        Observable.create<String> {
//            emitter ->
//            // do something and emit first item
//            if (!emitter.isDisposed) {
//                emitter.onNext("One")
//            }
//            // do something and emit second item
//            if (!emitter.isDisposed) {
//                emitter.onNext("Two")
//            }
//            // on complete
//            if (!emitter.isDisposed) {
//                emitter.onComplete()
//            }
//        }
//        .subscribeOn(Schedulers.io()).subscribe {
//            item -> System.out.println("CreateExample" + item)
//        }
//    }
//
//    private static void fromCallable() {
//        Observable.fromCallable({
//            return "Some Emission";
//        })
//        Observable.fromCallable<String> {
//            // do something and return
//            return @fromCallable "MindOrks"
//        }
//.subscribeOn(Schedulers.io()).subscribe {
//            item -> Log.d("FromCallableExample", "item : $item")
//        }
//    }
}
