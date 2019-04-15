package com.netty.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Set集合的底层就是Map
 * Set集合的特点是：元素不可重复
 * HashSet 底层数据结构是哈希表(是一个元素为链表的数组)
 * LinkedHashSet 底层数据结构由哈希表(是一个元素为链表的数组)和双向链表组成。
 * TreeSet 底层数据结构是红黑树(是一个自平衡的二叉树)，保证元素的排序方式
 */
public class SetTest {
    /**
     * HashSet 底层实际上是一个HashMap实例
     * HashSet 实际上就是封装了HashMap，操作HashSet元素实际上就是操作HashMap。
     * key就是set 元素，value就是内置默认object
     * 允许元素为null
     * 不保证迭代顺序
     * 非同步
     */
    public static void testHashSet(){
        HashSet set = new HashSet();
        set.add("set1");
    }

    /**
     * TreeSet 底层实际上是一个TreeMap实例
     * TreeSet 实际上就是封装了TreeMap，操作TreeSet元素实际上就是操作TreeMap。
     * key就是set 元素，value就是内置默认object
     * 实现NavigableSet接口
     * 可以实现排序功能
     * 非同步
     */
    public static void testTreeSet(){
        TreeSet set = new TreeSet();
        set.add("set1");
    }

    /**
     * LinkedHashSet 底层就是 LinkedHashMap
     * LinkedHashSet 实际上就是封装了 LinkedHashMap，操作 LinkedHashSet 元素实际上就是操作 LinkedHashMap。
     * key就是set 元素，value就是内置默认object
     */
    public static void testLinkedHashSet(){
        LinkedHashSet set = new LinkedHashSet();
        set.add("set1");
    }
}
