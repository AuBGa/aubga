package com.aubga.java.currentArt.chapter01;

import java.util.concurrent.LinkedTransferQueue;

public class VolatileTest {
    public volatile  int count = 0;

    public void counter() {
        count++;
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.counter();

        LinkedTransferQueue ltq = new LinkedTransferQueue();
        ltq.add(1);

        System.out.println(ltq.size());
    }
}


