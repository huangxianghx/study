package com.netty.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static CountDownLatch latch = new CountDownLatch(100);


    public static class Thread1 implements Runnable{
        private int count = 0;
        @Override
        public void run() {
            count++;
            latch.countDown();
        }
        public int getCount(){
            return count;
        }
    }

    public static class Thread2 implements Runnable{
        private AtomicInteger count = new AtomicInteger(0);
        @Override
        public void run() {
            count.incrementAndGet();
            latch.countDown();
        }
        public int getCount(){
            return count.get();
        }
    }

    public static class Thread3 implements Runnable{
        private int count = 0;

        @Override
        public synchronized void run() {
            count = count+1;
            latch.countDown();
        }

        public int getCount(){
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        for(int i=0;i<100;i++){
            executorService.submit(thread1);
            executorService.submit(thread2);
            executorService.submit(thread3);
        }
        //等到所有的线程都执行了countDown一次之后，在继续向下执行
        latch.await();
        System.out.println(thread1.getCount());
        System.out.println(thread2.getCount());
        System.out.println(thread3.getCount());
    }
}
