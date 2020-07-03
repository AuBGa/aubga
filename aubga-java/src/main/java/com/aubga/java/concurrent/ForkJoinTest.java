package com.aubga.java.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) {

        ForkJoinPool fjp = new ForkJoinPool(3);
        Fibonacci fib = new Fibonacci(30);

        Integer result = fjp.invoke(fib);
        System.out.println(result);

       // ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n) {
            this.n = n;
        }

        protected Integer compute() {
            if(n<=1) {
                return 1;
            }
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();

            Fibonacci f2 = new Fibonacci(n-2);
            return f2.compute()+f1.join();
        }
    }
}
