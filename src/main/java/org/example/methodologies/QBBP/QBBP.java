package org.example.methodologies.QBBP;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class QBBP {

    public static void main(String[] args) {


        //Creating queue for batch storage
        BlockingQueue<StringBuilder> blockingQueue = new LinkedBlockingDeque<StringBuilder>();


        //Defining queue populating class
        Queue_Populating_Producer_Class producer = new Queue_Populating_Producer_Class(blockingQueue);

        //Defining data processing classes
        Data_Processing_Consumer_Class consumer1 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer2 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer3 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer4 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer5 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer6 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer7 =new Data_Processing_Consumer_Class(blockingQueue);
        Data_Processing_Consumer_Class consumer8 =new Data_Processing_Consumer_Class(blockingQueue);


        //Define how many threads are needed
        Thread producerThread = new Thread(producer);
        Thread consumer1Thread = new Thread(consumer1);
        Thread consumer2Thread = new Thread(consumer2);
        Thread consumer3Thread = new Thread(consumer3);
        Thread consumer4Thread = new Thread(consumer4);
        Thread consumer5Thread = new Thread(consumer5);
        Thread consumer6Thread = new Thread(consumer6);
        Thread consumer7Thread = new Thread(consumer7);
        Thread consumer8Thread = new Thread(consumer8);

        //Starting threads
        producerThread.start();
        consumer1Thread.start();
        consumer2Thread.start();
        consumer3Thread.start();
        consumer4Thread.start();
        consumer5Thread.start();
        consumer6Thread.start();
        consumer7Thread.start();
        consumer8Thread.start();







    }
}
