package dev.spider.db;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class VAA implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger(100);
    int count = 100;


    @Override
    public void run() {
        count--;
        atomicInteger.decrementAndGet();
        System.out.println(Thread.currentThread().getName() + "-" + atomicInteger.get());
        System.out.println(Thread.currentThread().getName() + "-" + count);
    }
}

public class VA {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new VAA());
        Thread t2 = new Thread(new VAA());
        t1.setName("c1");
        t2.setName("c2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(2);
    }
}