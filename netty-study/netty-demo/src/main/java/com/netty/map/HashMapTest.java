//package com.netty.map;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * JDK1.8 HashMap 内部数据结构为数组 + 链表 + 红黑树
// * 由于底层是用数组存储元素，当数据没有出现哈希冲突时，查询时间复杂度为O(1)
// * 当链表size > 8时，会将链表转换为红黑树，原因是因为链表查找时间复杂度为O(n)，红黑树查找时间复杂度为O(log2n)
// * 当HashMap put操作执行完成后，容量超过设定扩容阈值=capacity * load factor，则会对HashMap进行扩容
// * 扩容逻辑：容量*2，并将原数据重新rehash到新扩容的结构
// *
// * 适用场景：
// */
//public class HashMapTest {
//    /**
//     * HashMap源码解析
//     */
//    public static void testHashMap(){
//        /**
//         * 默认容量=16，默认扩容因子=0.75
//         * 初始化只是设置了扩容阈值：threshold= capacity * load factor、扩容因子：loadFactor
//         * 并未初始化内部数组分配空间
//         */
//        //初始化容量=2，扩容因子=0.5，当put第二个元素时就会触发扩容逻辑
//        HashMap map = new HashMap(2,0.5f);
//        /**
//         * HashMap是在第一次put元素的时候通过resize()方法初始化内部数组tableNode<K,V>[]
//         *
//         */
//        map.put("1","1");
//        /**
//         * HashMap在达到扩容阈值之后，会通过resize()方法对数组扩容，扩容后容量为原容量*2
//         * 扩容完成后，会将原数组内的元素rehash到新扩容的数组
//         */
//        map.put("2","2");
//        map.put("3","3");
//        /**
//         * 根据key hash定位到元素位置
//         * 如果元素不存在，则直接插入新元素
//         * 如果可以直接定位到元素 / 如果元素next不为空：属于链表/红黑树，则遍历链表/红黑树获取元素
//         * 如果定位到的元素不为空，根据节点所属类型用新值替换旧值
//         */
//        map.put("2","2");
//        /**
//         * 根据key hash定位到元素位置
//         * 如果元素不存在，则返回null
//         * 如果可以直接定位到元素 / 如果元素next不为空：属于链表/红黑树，则遍历链表/红黑树获取元素
//         * 如果定位到的元素不为空，根据节点所属类型调用对应方法remove元素
//         */
//        map.remove("1");
//        /**
//         * 每次进行put/remove操作时，都会更新内部size变量
//         */
//        System.out.println("map size:"+map.size());
//        /**
//         * HashMap的keySet是乱序的
//         */
//        map.keySet().forEach(key->{
//            System.out.println("key:"+key);
//        });
//        Set<Map.Entry> entrySet = map.entrySet();
//        entrySet.forEach(entry->{
//            System.out.println("key:"+entry.getKey());
//            System.out.println("value:"+entry.getValue());
//        });
//    }
//
//    public static void main(String[] args) {
//        testHashMap();
//    }
//}
