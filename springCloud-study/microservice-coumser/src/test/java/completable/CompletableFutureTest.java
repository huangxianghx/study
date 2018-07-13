package completable;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2017年05月17日
 */
public class CompletableFutureTest{
    @Test
    public void test(){
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));
    }

    @Test
    public void test1(){

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            return 100;
        });
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>();
        list.add("from1");
        list.add("from2");
        list.add("from3");
        Observable fromObservable = Observable.from(list);

        fromObservable.subscribe(ss->{
            System.out.println(ss);
        });

        Observable.create(sub ->sub.onNext("Hi，Weavey！"))
                .subscribe(s -> System.out.print(s));

        Observable aa=Observable.create(subscriber ->
        {
            try {
                Thread.sleep(1000);
                subscriber.onNext("create1"); //发射一个"create1"的String
                Thread.sleep(2000);
                subscriber.onNext("create2"); //发射一个"create2"的String
                Thread.sleep(3000);
                int i=1/0;
//                subscriber.onCompleted();//发射完成,这种方法需要手动调用onCompleted，才会回调Observer的onCompleted方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Subscriber subscriber=new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("finish");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o.toString());
            }
        };
        aa.subscribe(subscriber);
    }

}
