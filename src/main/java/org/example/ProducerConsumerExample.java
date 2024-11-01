package org.example;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerExample {

    public static void main(String[] args) {

        BlockingQueue<StringBuilder> blockingQueue = new LinkedBlockingDeque<StringBuilder>();


        Queue_Populating_Producer_Class producer = new Queue_Populating_Producer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer1 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer2 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer3 =new Data_Processing_Consumer_Class(blockingQueue);


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
