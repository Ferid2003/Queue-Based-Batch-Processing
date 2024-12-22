package org.example.methodologies.QBBP;

import org.example.Searching_Algorithms.KMP_String_Matching;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Data_Processing_Consumer_Class implements Runnable{

    BlockingQueue<StringBuilder> blockingQueue = null;


    public Data_Processing_Consumer_Class(BlockingQueue<StringBuilder> blockingQueue) {
        this.blockingQueue = blockingQueue;

    }

    @Override
    public void run() {
        String output_file = // Path to the output file

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output_file, true))) {
            while (true) {
                StringBuilder batch = this.blockingQueue.take();

                // Check if we get a "poison pill" to stop consumers
                if (batch.toString().equals("EOF")) {
                    break;  // Break out of loop
                }

                //Change pattern
                //Select searching algorithm

                KMP_String_Matching.KMPSearch("14", batch.toString(),writer);
                //TwoWayStringMatching.findAllOccurrences(pattern, batch.toString(),writer);
                //int q = 101;
                //Rabin_Karp.search(pattern, batch.toString(), q, writer);
                //Boyer_Moore.search(batch.toString().toCharArray(), pattern.toCharArray(),writer);


                writer.flush();
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
