package org.example.methodologies.QBBP;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class Queue_Populating_Producer_Class implements Runnable {

    BlockingQueue<StringBuilder> blockingQueue = null;


    public Queue_Populating_Producer_Class(BlockingQueue<StringBuilder> blockingQueue) {
        this.blockingQueue = blockingQueue;

    }

    @Override
    public void run() {
        String inputFilePath =  // Path to the input file
        // Batch size defines how many lines to process in one batch
        int batchSize = 128000;
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
            //!!!!!IMPORTANT!!!!!
            //Change the number in "i < num" to match the number of data processing threads used to avoid infinite loop
            //Example: if 4 data processing threads are used "int i =0; i<4; i++"
            for (int i = 0; i < 8; i++){
                this.blockingQueue.put(new StringBuilder("EOF"));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
