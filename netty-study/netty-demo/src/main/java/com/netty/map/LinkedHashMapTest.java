//package com.netty.map;
//
//import java.util.*;
//
///**
// * LinkedHashMap继承于HashMap，基础特性与HashMap一致
// * LinkedHashMap内部维持了一个链表结构，通过HashMap预埋的afterNodeAccess、afterNodeInsertion、afterNodeRemoval保证了get/put/remove的顺序性保证
// * LinkedHashMap是有序的，HashMap是无序的
// * LinkedHashMap默认排序顺序为插入顺序
// */
//public class LinkedHashMapTest {
//
//    public static void testLinkedHashMap(){
//        LinkedHashMap map = new LinkedHashMap(2,0.5f);
//        map.put("1","1");
//        map.put("2","2");
//        map.put("3","3");
//
//    }
//
//    public static void compareHashAndLinked(){
//        HashMap hashMap = new HashMap();
//        LinkedHashMap linkedHashMap = new LinkedHashMap();
//        for(int i=1;i<10;i++){
//            hashMap.put(i,i);
//            linkedHashMap.put(i,i);
//        }
//        Set<Map.Entry> hashEntrySet = hashMap.entrySet();
//        Set<Map.Entry> linkEntrySet = linkedHashMap.entrySet();
//        Iterator<Map.Entry> hashIterable = hashEntrySet.iterator();
//        while(hashIterable.hasNext()){
//            Map.Entry entry =  hashIterable.next();
//            System.out.println("hash--> key:" + entry.getKey() + " value:" +entry.getValue());
//        }
//
//        Iterator<Map.Entry> linkIterable = linkEntrySet.iterator();
//        while(linkIterable.hasNext()){
//            Map.Entry entry =  linkIterable.next();
//            System.out.println("link--> key:" + entry.getKey() + " value:" +entry.getValue());
//        }
//    }
//
//    public static void main(String[] args) {
////        testLinkedHashMap();
//        compareHashAndLinked();
//    }
//}
