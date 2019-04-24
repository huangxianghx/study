package com.netty.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Collection叫做集合，它可以快速查找现有的元素。
 * 而Map中称之为-->映射
 * Map 集合的特点：将键映射到值的对象，一个映射不能包含重复的键，每个键最多只能映射到一个值
 * Map和Collection的区别：
 * 1、Map集合存储元素是成对出现的，Map的键是唯一的，值是可以重复的
 * 2、Collection集合存储元素是单独出现的，Collection的实现：Set是唯一的，List是可重复的
 * 要点：
 * 1、Map集合的数据结构针对键有效，跟值无关
 * 2、Collection集合的数据结构针对元素有效
 */
public class MapTest {

    /**
     * 除了允许为null和非同步以外，跟HashTable内部逻辑一样
     * 无序，允许为null，非同步
     * 底层由散列表(哈希表)实现
     * 初始容量和装载因子对HashMap影响挺大的，设置小了不好，设置大了也不好
     *
     * 链表和数组都可以按照人们的意愿来排列元素的次序，他们可以说是有序的(存储的顺序和取出的顺序是一致的)
     * 但同时，这会带来缺点：想要获取某个元素，就要访问所有的元素，直到找到为止。
     * 还有另外的一些存储结构：不在意元素的顺序，能够快速的查找元素的数据，其中就有一种非常常见的：散列表
     * 在Java中，散列表用的是链表 + 数组实现的，每个列表称之为桶。
     * 在JDK1.8中，桶满时会从链表变成平衡二叉树(红黑树)
     * 如果散列表太满，是需要对散列表再散列，创建一个桶数更多的散列表，并将原有的元素插入到新表中，丢弃原来的表
     *
     * JDK1.8 HashMap 内部数据结构为数组 + 链表 + 红黑树
     * 由于底层是用数组存储元素，当数据没有出现哈希冲突时，查询时间复杂度为O(1)
     * 当链表size > 8时，会将链表转换为红黑树，原因是因为链表查找时间复杂度为O(n)，红黑树查找时间复杂度为O(log2n)
     * 并不是桶子上有8位元素的时候它就能变成红黑树，它得同时满足我们的散列表容量大于64才行的
     * 当HashMap put操作执行完成后，容量超过设定扩容阈值=capacity * load factor，则会对HashMap进行扩容
     * 扩容逻辑：容量*2，并将原数据重新rehash到新扩容的结构
     * HashMap并不是直接拿key的哈希值来用的，它会将key的哈希值的高16位进行异或操作，使得我们将元素放入哈希表的时候增加了一定的随机性。
     */
    public static void testHashMap(){
        /**
         * 默认容量=16，默认扩容因子=0.75
         * 初始化只是设置了扩容阈值：threshold= capacity * load factor、扩容因子：loadFactor
         * 并未初始化内部数组分配空间
         */
        //初始化容量=2，扩容因子=0.5，当put第二个元素时就会触发扩容逻辑
        HashMap map = new HashMap(2,0.5f);
        /**
         * HashMap是在第一次put元素的时候通过resize()方法初始化内部数组tableNode<K,V>[]
         *
         */
        map.put("HashMap1","HashMap1");
        /**
         * HashMap在达到扩容阈值之后，会通过resize()方法对数组扩容，扩容后容量为原容量*2
         * 扩容完成后，会将原数组内的元素rehash到新扩容的数组
         */
        map.put("HashMap2","HashMap2");
        map.put("HashMap3","HashMap3");
        /**
         * 根据key hash定位到元素位置
         * 如果元素不存在，则直接插入新元素
         * 如果可以直接定位到元素 / 如果元素next不为空：属于链表/红黑树，则遍历链表/红黑树获取元素
         * 如果定位到的元素不为空，根据节点所属类型用新值替换旧值
         */
        map.put("HashMap2","HashMap2");
        /**
         * 根据key hash定位到元素位置
         * 如果元素不存在，则返回null
         * 如果可以直接定位到元素 / 如果元素next不为空：属于链表/红黑树，则遍历链表/红黑树获取元素
         * 如果定位到的元素不为空，根据节点所属类型调用对应方法remove元素
         */
        map.remove("HashMap1");
        /**
         * 每次进行put/remove操作时，都会更新内部size变量
         */
        System.out.println("map size:"+map.size());
        /**
         * HashMap的keySet是乱序的
         */
        map.keySet().forEach(key->{
            System.out.println("key:"+key);
        });
        Set<Map.Entry> entrySet = map.entrySet();
        entrySet.forEach(entry->{
            System.out.println("key:"+entry.getKey());
            System.out.println("value:"+entry.getValue());
        });
    }

    /**
     * JDK1.7的底层是：segments+HashEntry数组，Segment继承了ReentrantLock,每个片段都有了一个锁，叫做“锁分段”
     * JDK1.8的底层是：散列表+红黑树
     * ConCurrentHashMap 支持高并发的访问和更新，它是线程安全的
     * ConcurrentHashMap 作为一个高并发的容器，它是通过  部分锁定+CAS算法  来进行实现线程安全的。CAS算法也可以认为是乐观锁的一种
     * get操作不用加锁，get方法是非阻塞的，通过volatile修饰next来实现每次获取都是最新设置的值
     * key和value都不允许为null
     *
     * Hashtable是在每个方法上都加上了Synchronized完成同步，效率低下。
     * ConcurrentHashMap通过在部分加锁和利用CAS算法来实现同步。
     *
     * 只让一个线程对散列表进行初始化
     * put的元素如果再map中原本不存在时，不加锁，直接insert
     * 如果是替换的话，则用synchronized加锁保证线程安全
     * CAS算法：先比较是否相等，如果相等则替换
     * volatile经典总结：volatile仅仅用来保证该变量对所有线程的可见性，但不保证原子性
     *
     * ConcurrentHashMap用了cas锁、volatile等方式来实现线程安全
     */
    public static void testConcurrentHashMap(){
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("ConcurrentHashMap","ConcurrentHashMap");
        map.get("ConcurrentHashMap");
    }


    /**
     * LinkedHashMap 继承于HashMap，基础特性与HashMap一致
     * LinkedHashMap 底层是散列表 + 双向链表，通过HashMap预埋的afterNodeAccess、afterNodeInsertion、afterNodeRemoval保证了get/put/remove的顺序性
     * LinkedHashMap 是有序的，默认排序顺序为插入顺序，HashMap 是无序的，
     * LinkedHashMap 允许为null，非同步，线程不安全
     * LinkedHashMap可以设置两种遍历顺序：访问顺序（access-ordered）、插入顺序（insertion-ordered），默认是插入顺序的
     * 最常用的将其放在链表的最后，不常用的放在链表的最前，内部为LRU算法
     */
    public static void testLinkedHashMap(){
        LinkedHashMap map = new LinkedHashMap();
    }

    /**
     * TreeMap底层都是红黑树来实现的
     * TreeMap底层是红黑树，它方法的时间复杂度都不会太高:log(n)
     * TreeMap非同步的，想要同步可以使用Collections来进行封装
     * TreeMap实现了NavigableMap接口，而NavigableMap接口继承着SortedMap接口，致使我们的TreeMap是有序的
     * 使用Comparator或者Comparable来比较key是否相等与排序的问题，如果comparator为null，那么就使用自然顺序
     * compare,小于则左边，大于则右边，递归排序
     */
    public static void testTreeMap(){
        TreeMap map = new TreeMap();
        map.put("TreeMap","TreeMap");
    }

    public static void main(String[] args) {
        testHashMap();
        testConcurrentHashMap();
        testLinkedHashMap();
        testTreeMap();
    }
}
