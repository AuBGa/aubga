package com.aubga.java.currentArt.chapter06;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentContainer {
    private Map map = new HashMap();
    private Map currentMap = new ConcurrentHashMap();

    private Queue queue = new ConcurrentLinkedDeque();

    @Test
    public void put() {
        map.put("aaa",111);
        currentMap.put("ccc",333);
    }

    @Test
    public void get() {
        map.get("aaa");
        currentMap.get("ccc");
    }

    @Before
    public void init() {
        map.put("bbb",222);
        currentMap.put("bbb",222);
    }

    @Test
    public void offer() {
        queue.offer("a");
        queue.offer("b");
        System.out.println(queue.size());
    }

    @Test
    public void conti() {

        restartFromTail:
        for(int i=0;i<1000;i++) {
            if(i == 500) {
                System.out.println("curr is 500 ,reborn");
                continue restartFromTail;
            }
        }
        System.out.println("loop over");
    }

    @Test
    public void block() throws InterruptedException{
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2,true);
        queue.add(1);
        //queue.add(2);
        queue.put(2);

        boolean flag = queue.offer(3);
        System.out.println(flag);
        System.out.println(queue.size());


        LinkedBlockingDeque linkedQueue = new LinkedBlockingDeque();
        linkedQueue.add(1);
        linkedQueue.add(2);
        System.out.println(linkedQueue.size());

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();


        PriorityBlockingQueue priorityQueue = new PriorityBlockingQueue(100, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        priorityQueue.add(1);
        priorityQueue.add(2);
        System.out.println(priorityQueue.size());

        DelayQueue delayQueue = new DelayQueue();
        delayQueue.add(new Delayer(new Date().getTime()));
        TimeUnit.SECONDS.sleep(1);
        delayQueue.add(new Delayer(new Date().getTime()));
        TimeUnit.SECONDS.sleep(1);
        delayQueue.add(new Delayer(new Date().getTime()));

        System.out.println(delayQueue.size());
        System.out.println(delayQueue);
        Iterator it =  delayQueue.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    class Delayer implements Delayed {

        private long time;

       // public Delayer(){}
        public Delayer(long time) {
            this.time = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - new Date().getTime(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o1) {

            return 0;
        }

        @Override
        public String toString() {
            return "time:"+time;
        }
    }
}
