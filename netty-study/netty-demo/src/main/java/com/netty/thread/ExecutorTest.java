package com.netty.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService 线程池
 * 生产者-消费者模式
 * 提交任务到线程池后，将任务加入阻塞队列，线程池异步从阻塞队列获取Runnable任务执行
 * corePoolSize 核心线程数，创建完成之后不会随着keepAliveTime的空闲时间释放。
 * 如果线程池内的活动线程尚未达到核心线程数，线程池会继续创建新线程
 */
public class ExecutorTest {
    /**
     * CachedThreadPool
     * 根据实际运行情况动态调整线程大小的线程池
     * corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，keepAliveTime=60s，blockQueue=SynchronousQueue
     * SynchronousQueue：没有数据缓存空间的阻塞队列，生产者和消费者互相等待对方，握手，然后一起离开。强制FIFO有序排列
     */
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    /**
     * fixedThreadPool
     * 固定线程数的线程池
     */
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);


    public static void main(String[] args) {
        cachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("cache");
            }
        });
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("fixed");
            }
        });
        singleThreadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("single");
            }
        });
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduled");
            }
        },2, 1,TimeUnit.SECONDS);
    }

}
