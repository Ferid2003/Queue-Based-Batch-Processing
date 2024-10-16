package org.example.pc;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Producer implements Runnable {

    BlockingQueue<StringBuilder> blockingQueue = null;

    public Producer(BlockingQueue<StringBuilder> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String inputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\evaluation_ids.csv";   // Path to the input file
        // Batch size defines how many lines to process in one batch
        int batchSize = 64000;
        int batchCounter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;
            StringBuilder batchData = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Add line to batch data without processing it yet
                batchData.append(line).append(System.lineSeparator());
                batchCounter++;

                // When batch size is reached, process and write the batch to the output file
                if (batchCounter >= batchSize) {
                    this.blockingQueue.put(new StringBuilder(batchData.toString()));
                    batchData.setLength(0); // Clear the batch data
                    batchCounter = 0;
                }
            }

            // Process and write any remaining data to the output file
            if (batchCounter > 0) {
                this.blockingQueue.put(new StringBuilder(batchData.toString()));

            }




        } catch (IOException | InterruptedException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
        try {
            this.blockingQueue.put(new StringBuilder("EOF"));
            this.blockingQueue.put(new StringBuilder("EOF"));
            this.blockingQueue.put(new StringBuilder("EOF"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
