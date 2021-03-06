package com.netty.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List集合的特点就是：有序(存储顺序和取出顺序一致),可重复
 * List中又有它自己对应的实现-->ListIterator接口，ListIterator可以往前遍历，添加元素，设置元素
 * ArrayList==>数组==>查询快，增删慢； LinkedList==>链表==>增删快、查询慢
 * 总的来说：查询多用ArrayList，增删多用LinkedList。
 * CopyOnWriteArrayList是同步List的替代品，CopyOnWriteArraySet是同步Set的替代品。
 * 无论是Hashtable-->ConcurrentHashMap，还是说Vector-->CopyOnWriteArrayList。
 * JUC下支持并发的容器与老一代的线程安全类相比，总结起来就是加锁粒度的问题
 *
 * Hashtable、Vector加锁的粒度大(直接在方法声明处使用synchronized)
 * ConcurrentHashMap、CopyOnWriteArrayList加锁粒度小
 * 用各种的方式来实现线程安全，比如我们知道的ConcurrentHashMap用了cas锁、volatile等方式来实现线程安全
 * JUC下的线程安全容器在遍历的时候不会抛出ConcurrentModificationException异常
 */
public class ListTest {

    /**
     * ArrayList 底层数据结构是数组。线程不安全
     * 如果想要ArrayList实现同步，可以使用Collections的方法：List list = Collections.synchronizedList(new ArrayList(...));
     * ArrayList 底层其实就是一个数组，ArrayList中有扩容这么一个概念，正因为它扩容，所以它能够实现“动态”增长
     * ArrayList 是基于动态数组实现的，在增删时候，需要数组的拷贝复制(navite 方法由C/C++实现)。
     * ArrayList 的默认初始化容量是10，每次扩容时候增加原先容量的一半，也就是变为原来的1.5倍
     * 删除元素时不会减少容量，若希望减少容量则调用trimToSize()
     * 它不是线程安全的。它能存放null值。
     * 由于内部结构是数组（基于连续地址存储）add/remove元素的时候，如果不是从尾部操作，则需要移动元素位置，所以空间复杂度为O(n)，
     * 但是get元素的时候，由于底层数据连续存储，可以通过数组下标获取数据存储指针，所以时间复杂度为O(1)。
     */
    public static void testArrayList(){
        /**
         * 创建线程安全的ArrayList，内部用synchronized关键字实现
         * synchronized不是加在方法的声明处，而是方法的内部
         */
        Collections.synchronizedList(new ArrayList());
        //默认创建对象，初始化一个空数组
        ArrayList<String> list = new ArrayList();
        /**
         * add元素时，如果是第一次（初始化数组为空），默认分配大小为10的数组空间
         * add方法步骤：1、检查是否需要扩容 2、插入元素
         * 扩容到原来的1.5倍，
         * 第一次扩容后，如果容量还是小于minCapacity，就将容量扩充为minCapacity。
         * 足够：直接添加
         * 不足够：扩容
         */
        list.add("list1");
        /**
         * add元素，会先判断数组add之后是否超过了数组容量
         * 如果超过了，默认会将容量扩容50%后
         * 如果容量扩容50%还是不能满足当前数组元素，则以当前数组元素最大值进行扩容
         * 扩容完成后，将当前所有元素复制到新数组
         */
        list.add("list2");
        list.add("list3");
        /**
         * 检查下标是否越界
         * 空间检查，如果有需要进行扩容
         * 插入元素
         */
        list.add(3,"list4");
        /**
         * 检查下标是否越界
         * 替换新值
         * 返回旧值
         */
        String oldVlue = list.set(3,"list4BySet");
        /**
         * 检查下标是否越界
         * 删除对应元素
         * 计算出需要移动的个数，并移动
         * 设置为null，让Gc回收
         */
        list.remove("list1");
        /**
         * 检查下标是否越界
         * 返回具体元素
         */
        list.get(1);
        /**
         * ListIterator可以往前遍历，添加元素，设置元素
         */
        ListIterator listIterator = list.listIterator();
        while(listIterator.hasNext()){
            System.out.println("ArrayList ListIterator:"+listIterator.next());
        }
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println("ArrayList Iterator:"+iterator.next());
        }
    }

    /**
     * LinkedList 底层是双向链表[双向链表方便实现往前遍历]
     * LinkedList 实现了Deque接口，因此，我们可以操作LinkedList像操作队列和栈一样~
     *
     * LinkedList的元素添加和删除其实就对应着链表节点的添加和移除。所以新增/删除空间复杂度为O(1)，查询时间复杂度为O(n)
     */
    public static void testLinkedList(){
        LinkedList linkedList = new LinkedList();
        /**
         * add方法实际上就是往链表最后添加元素
         */
        linkedList.add("ll1");
        linkedList.add(1,"ll2");
        /**
         * 检查下标是否越界
         * 删除链表对应元素
         * 找到元素的prev、next，将prev的next指向next，next的prev指向prev
         */
        linkedList.remove(1);
        /**
         * 检查下标是否越界
         * 根据二分法，如果下标>长度的一半，从尾部开始遍历，如果下标<长度的一半，从头部开始遍历
         */
        linkedList.get(0);
        /**
         * 与get方法类似
         * 检查下标是否越界
         * 根据二分法，如果下标>长度的一半，从尾部开始遍历，如果下标<长度的一半，从头部开始遍历
         */
        linkedList.set(0,"ll3");
        ListIterator listIterator = linkedList.listIterator();
        while(listIterator.hasNext()){
            System.out.println("LinkedList ListIterator:"+listIterator.next());
        }
    }

    /**
     * Vector 底层数据结构是数组。线程安全
     * Vector 底层也是数组，与ArrayList最大的区别就是：同步(线程安全)
     * ArrayList 在底层数组不够用时在原来的基础上扩展0.5倍，Vector是扩展1倍。
     * Vector所有方法都是同步，有性能损失。
     * Vector初始length是10 超过length时 以100%比率增长，相比于ArrayList更多消耗存储空间。
     */
    public static void testVector(){
        Vector vector = new Vector();
        /**
         * 底层逻辑与ArrayList一样，不过都是用synchronized同步
         */
        vector.add("v1");
        vector.add(1,"v2");
        vector.set(1,"v3");
        ListIterator listIterator = vector.listIterator();
        while(listIterator.hasNext()){
            System.out.println("Vector ListIterator:"+listIterator.next());
        }
    }

    /**
     * CopyOnWriteArrayList是线程安全容器(相对于ArrayList)，底层通过复制数组的方式来实现。
     * CopyOnWriteArrayList在遍历的使用不会抛出ConcurrentModificationException异常，并且遍历的时候就不用额外加锁
     * 元素可以为null
     *
     * CopyOnWriteArrayList底层就是数组，加锁就交由ReentrantLock来完成。
     *
     * 如果遍历Vector/SynchronizedList是需要自己手动加锁的
     * CopyOnWriteArrayList在使用迭代器遍历的时候，操作的都是原数组！所以不用加锁
     *
     * 在修改时，复制出一个新数组，修改的操作在新数组中完成，最后将新数组交由array变量指向。
     * 写加锁，读不加锁
     *
     * CopyOnWriteArrayList的缺点了。
     * 内存占用：如果CopyOnWriteArrayList经常要增删改里面的数据，经常要执行add()、set()、remove()的话，那是比较耗费内存的。
     * 因为我们知道每次add()、set()、remove()这些增删改操作都要复制一个数组出来。
     * 数据一致性：CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。
     * 从上面的例子也可以看出来，比如线程A在迭代CopyOnWriteArrayList容器的数据。线程B在线程A迭代的间隙中将CopyOnWriteArrayList部分的数据修改了(已经调用setArray()了)。但是线程A迭代出来的是原有的数据。
     */
    public static void testCopyOnWriteArrayList(){
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        /**
         * 在添加的时候就上锁，并复制一个新数组，增加操作在新数组上完成，将array指向到新数组中，最后解锁。
         */
        copyOnWriteArrayList.add("copyList");
    }

    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testVector();
        testCopyOnWriteArrayList();
    }
}
