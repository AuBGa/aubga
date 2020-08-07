package com.aubga.alog.skiplist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhuxinquan on 17-3-11.
 */
public class SkipListTest {
    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        SkipList skipList = new SkipList(12);
        Set<Integer> set = new HashSet();
        List<Integer> list = new LinkedList<>();

        //测试跳跃表性能
        startTime = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            skipList.insert(i);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("createSkipList:%d\n", endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.printf("find(555)：%d\n", skipList.find(55555).getNext().getValue());
        endTime = System.currentTimeMillis();
        System.out.printf("skipListFindTime:%d\n\n", endTime - startTime);

        //测试LinkedList性能
        startTime = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            list.add(i);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("createList:%d\n", endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.printf("find(555)：%b\n", list.contains(55555));
        endTime = System.currentTimeMillis();
        System.out.printf("linkedListFindTime:%d\n\n", endTime - startTime);

        //测试hashSet性能
        startTime = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            set.add(i);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("createSet:%d\n", endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.printf("find(555)：%b\n", set.contains(55555));
        endTime = System.currentTimeMillis();
        System.out.printf("hashSetFindTime:%d\n", endTime - startTime);
    }
}
