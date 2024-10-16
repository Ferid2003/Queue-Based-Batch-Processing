package org.example.pc;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerExample {

    public static void main(String[] args) {

        BlockingQueue<StringBuilder> blockingQueue = new LinkedBlockingDeque<StringBuilder>();


        Producer producer = new Producer(blockingQueue);
        Consumer consumer1 =new Consumer(blockingQueue);
        Consumer consumer2 =new Consumer(blockingQueue);
        Consumer consumer3 =new Consumer(blockingQueue);


        Thread producerThread = new Thread(producer);
        Thread consumer1Thread = new Thread(consumer1);
        Thread consumer2Thread = new Thread(consumer2);
        Thread consumer3Thread = new Thread(consumer3);

        producerThread.start();
        consumer1Thread.start();
        consumer2Thread.start();
        consumer3Thread.start();



    }
}
