### Executor线程池

> Executor线程池框架的最大优点是把任务的提交和执行解耦（生产者-消费者模式）。客户端将要执行的任务封装成Task，然后提交即可。
而Task如何执行客户端则是透明的。具体点讲，提交一个Callable对象给ExecutorService（如最常用的线程池ThreadPoolExecutor），
将得到一个Future对象，调用Future对象的get方法等待执行结果。

#### 线程池优点
* <font color=red>`降低资源消耗`</font>。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
* <font color=red>`提高响应速度`</font>。当任务到达时，任务可以不需要等到线程创建就能立即执行。
* <font color=red>`提高线程的可管理性`</font>。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。

#### 线程池任务执行流程：
* 从线程池中获取可用线程执行任务，如果没有可用线程则使用ThreadFactory创建新的线程，直到线程数达到corePoolSize限制
* 线程池线程数达到corePoolSize以后，新的任务将被放入队列，直到队列不能再容纳更多的任务
* 当队列不能再容纳更多的任务以后，会创建新的线程，直到线程数达到maximumPoolSize限制
* 线程数达到maximumPoolSize限制以后新任务会被拒绝执行，调用 RejectedExecutionHandler 进行处理

#### 构造函数参数解析

```
//构造函数
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
```

**corePoolSize**
> <font color=red>`核心线程数`</font>，线程池核心线程数量。一旦创建将不会再释放。如果创建的线程数还没有达到指定的核心线程数量，将会继续创建新的核心线程，直到达到最大核心线程数后，核心线程数将不在增加；
如果没有空闲的核心线程，同时又未达到最大线程数，则将继续创建非核心线程；
如果线程数已经达到最大线程数，任务将被拒绝。

**maximumPoolSize**
> <font color=red>`最大线程数`</font>，线程池最大线程数量

**keepAliveTime**
> <font color=red>`线程最大存活时间`</font>，线程KeepAlive时间，当线程池数量超过核心线程数量以后，idle时间超过这个值的非核心线程会被终止

**unit** 
> <font color=red>`存活时间单位`</font>，TimeUnit.SECONDS等。

**workQueue**
> <font color=red>`任务队列`</font>，存储暂时无法执行的任务，等待空闲线程来执行任务。

**threadFactory** 
> <font color=red>`线程工厂`</font>，创建线程的工厂对象

**RejectedExecutionHandler**
> <font color=red>`拒绝策略`</font>，当线程池没有空闲线程时，拒绝新任务的策略。

#### 线程池类型
##### CachedThreadPool
**源码解析**
```
 //创建方式
 Executors.newCachedThreadPool();
 /**
 * 构造函数
 * corePoolSize=0 核心线程数为0
 * maximumPoolSize=Integer.MAX_VALUE 最大线程数为int最大数
 * keepAliveTime=60s 线程空闲存活时间为60s
 * wordQueue=SynchronousQueue SynchronousQueue是无界的，是一种无缓冲的等待队列，但是由于该Queue本身的特性，在某次添加元素后必须等待其他线程取走后才能继续添加
 */
 public static ExecutorService newCachedThreadPool() {
         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                       60L, TimeUnit.SECONDS,
                                       new SynchronousQueue<Runnable>());
     }
 
```

**通俗介绍**
> 当有新任务到来，则插入到SynchronousQueue中，由于SynchronousQueue是同步队列，因此会在线程池中寻找可用线程来执行，若有可用线程则执行，若没有可用线程则创建一个线程来执行该任务；若池中线程空闲时间超过指定大小，则该线程会被销毁。

**特点**
> 对任务的处理策略是提交的任务会立即分配一个线程进行执行，线程池中线程数量会随着任务数的变化自动扩张和缩减，在任务执行时间无限延长的极端情况下会创建过多的线程。

**适用场景**
> 执行很多短期异步的小程序或者负载较轻的服务器

##### fixedThreadPool
**源码解析**
```
 //创建方式
 Executors.newFixedThreadPool(4);
 /**
 * 构造函数
 * corePoolSize=maximumPoolSize=nThreads 核心线程数=最大线程数，通过构造函数初始化
 * keepAliveTime=0s 固定线程池不会创建超过核心线程数的线程，因此不会出现空闲线程
 * wordQueue=LinkedBlockingQueue LinkedBlockingQueue是无界的，是一个无界缓存的等待队列。
                                 基于链表的阻塞队列，内部维持着一个数据缓冲队列（该队列由链表构成）。当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，并缓存在队列内部，而生产者立即返回；只有当队列缓冲区达到最大值缓存容量时（LinkedBlockingQueue可以通过构造函数指定该值），才会阻塞生产者队列，直到消费者从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。
                                 LinkedBlockingQueue之所以能够高效的处理并发数据，还因为其对于生产者端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。
 */
 public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
 
```

**通俗介绍**
> 创建可容纳固定数量线程的池子，每个线程的存活时间是无限的，当池子满了就不在添加线程了；如果池中的所有线程均在繁忙状态，对于新任务会进入阻塞队列中(无界的阻塞队列)

**特点**
>* 优点是能够保证所有的任务都被执行，永远不会拒绝新的任务；
>* 缺点是队列数量没有限制，在任务执行时间无限延长的这种极端情况下会造成内存问题。

**适用场景**
> 执行长期的任务，性能好很多

##### SingleThreadExecutor
**源码解析**
```
 //创建方式
 Executors.newSingleThreadExecutor();
 /**
 * 构造函数
 * corePoolSize=maximumPoolSize=1 核心线程数=最大线程数=1，单线程
 * keepAliveTime=0s 单线程不会创建超过核心线程数的线程，因此不会出现空闲线程
 * wordQueue=LinkedBlockingQueue LinkedBlockingQueue是无界的，是一个无界缓存的等待队列。
                                 基于链表的阻塞队列，内部维持着一个数据缓冲队列（该队列由链表构成）。当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，并缓存在队列内部，而生产者立即返回；只有当队列缓冲区达到最大值缓存容量时（LinkedBlockingQueue可以通过构造函数指定该值），才会阻塞生产者队列，直到消费者从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。
                                 LinkedBlockingQueue之所以能够高效的处理并发数据，还因为其对于生产者端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。
 */
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }

 
```

**通俗介绍**
> 创建只有一个线程的线程池，且线程的存活时间是无限的；当该线程正繁忙时，对于新任务会进入阻塞队列中(无界的阻塞队列)

**特点**
>* 适用于在逻辑上需要单线程处理任务的场景，同时无界的LinkedBlockingQueue保证新任务都能够放入队列，不会被拒绝
>* 缺点和FixedThreadPool相同，当处理任务无限等待的时候会造成内存问题

**适用场景**
> 一个任务一个任务执行的场景

##### ScheduledThreadPool
**源码解析**
```
 //创建方式
 ExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
 /**
 * 构造函数
 * corePoolSize=corePoolSize 核心线程数，通过构造函数初始化
 * maximumPoolSize=Integer.MAX_VALUE 最大线程数为int最大数
 * keepAliveTime=0s 
 * wordQueue=DelayedWorkQueue DelayedWorkQueue 优先级队列，它会对插入的数据进行优先级排序，保证优先级越高的数据首先被获取，与数据的插入顺序无关。
                                               如果我们想实现延时或者定时执行任务，重要一点就是任务队列会根据任务延时时间的不同进行排序，延时时间越短地就排在队列的前面，先被获取执行。
 */
public ScheduledThreadPoolExecutor(int corePoolSize) {
    super(corePoolSize, Integer.MAX_VALUE,
          DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
          new DelayedWorkQueue());
}
 
```

**通俗介绍**
> 创建一个固定大小的线程池，线程池内线程存活时间无限制，线程池可以支持定时及周期性任务执行，如果所有线程均处于繁忙状态，对于新任务会进入DelayedWorkQueue队列中，这是一种按照超时时间排序的队列结构

**适用场景**
> 周期性执行任务的场景