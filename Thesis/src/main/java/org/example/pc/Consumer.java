package org.example.pc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    // Measure start time
    long startTime = System.currentTimeMillis();
    BlockingQueue<StringBuilder> blockingQueue = null;

    public Consumer(BlockingQueue<StringBuilder> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("input-file", true))) {
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
