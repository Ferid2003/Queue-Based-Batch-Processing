package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Data_Processing_Consumer_Class implements Runnable{

    // Measure start time
    long startTime = System.currentTimeMillis();
    BlockingQueue<StringBuilder> blockingQueue = null;

    public Data_Processing_Consumer_Class(BlockingQueue<StringBuilder> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\output.txt", true))) {
            while (true) {
                StringBuilder batch = this.blockingQueue.take();

                // Check if we get a "poison pill" to stop consumers
                if (batch.toString().equals("EOF")) {
                    // Measure end time
                    long endTime = System.currentTimeMillis();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime + " ms");
                    break;  // Break out of loop when we receive end of file signal
                }

                writer.write(batch.toString().toUpperCase());
                writer.flush(); // Ensure data is written to the file
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
