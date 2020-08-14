package com.aubga.java.currentArt.chapter04;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool_2 {

    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool_2(int initialSize) {
        if(initialSize > 0) {
            for(int i=0;i<initialSize;i++) {
                pool.add(ConnectionDriver_2.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if(connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection  fetchConnection(long mills) throws  InterruptedException {
        synchronized (pool) {
            if(mills <= 0) {
                if(pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()) {
                    result   = pool.removeFirst();
                }
                return result;

            }

        }
    }
}
