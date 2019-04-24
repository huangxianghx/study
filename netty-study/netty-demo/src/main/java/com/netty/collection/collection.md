# Java集合

#### List
> List集合的特点就是：有序(存储顺序和取出顺序一致),可重复

* ArrayList
    * 123

* LinkedList

* Vector

* CopyOnWriteArrayList

#### Map
* HashMap

* LinkedHashMap

* TreeMap

* HashTable

```


```

* ConcurrentHashMap


#### Set
> Set集合的特点是：元素不可重复，
> Set集合的底层就是Map,

* HashSet
> 内部是用HashMap引用对象实现的，具体逻辑参考HashMap

* LinkedHashSet

* TreeSet

* CopyOnWriteArraySet
> 内部是用CopyOnWriteArrayList引用对象实现的


## 各个集合之前的线程安全实现
* CopyOnWriteArrayList是同步List的替代品，CopyOnWriteArraySet是同步Set的替代品，ConcurrentHashMap是同步Map的替代品
* HashTable、Vector加锁的粒度大(直接在方法声明处使用synchronized)
* ConcurrentHashMap、CopyOnWriteArrayList、CopyOnWriteArraySet加锁粒度小
* JUC下支持并发的容器与老一代的线程安全类相比，总结起来就是加锁粒度的问题
* JUC下的线程安全容器在遍历的时候不会抛出ConcurrentModificationException异常