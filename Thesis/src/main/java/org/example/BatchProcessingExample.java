package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//128000
//Elapsed time: 26229 ms        Elapsed time: 27998 ms      Elapsed time: 11872 ms
//Memory used: 34 MB            Memory used: 34 MB          Memory used: 33 MB

//64000
//Elapsed time: 26890 ms        Elapsed time: 27019 ms      Elapsed time: 26456 ms
//Memory used: 78 MB            Memory used: 79 MB          Memory used: 66 MB

//32000
//Elapsed time: 26455 ms        Elapsed time: 26405 ms      Elapsed time: 26236 ms
//Memory used: 137 MB           Memory used: 137 MB         Memory used: 137 MB

//16000
//Elapsed time: 30326 ms        Elapsed time: 28028 ms      Elapsed time: 26256 ms
//Memory used: 115 MB           Memory used: 120 MB         Memory used: 117 MB
public class BatchProcessingExample {


    public static void main(String[] args) {

        String inputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\evaluation_ids.csv";   // Path to the input file
        String outputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\output.txt"; // Path to the output file

        // Batch size defines how many lines to process in one batch
        int batchSize = 64000;
        int batchCounter = 0;

        // Measure start time
        long startTime = System.currentTimeMillis();

        // Measure memory before processing
        Runtime runtime = Runtime.getRuntime();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            StringBuilder batchData = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Add line to batch data without processing it yet
                batchData.append(line).append(System.lineSeparator());
                batchCounter++;

                // When batch size is reached, process and write the batch to the output file
                if (batchCounter >= batchSize) {
                    // Process the entire batch at once (e.g., convert to uppercase)
                    String processedBatch = batchData.toString().toUpperCase();

                    // Write processed batch to the output file
                    writer.write(processedBatch);
                    writer.flush(); // Ensure data is written to the file
                    batchData.setLength(0); // Clear the batch data
                    batchCounter = 0;
                }
            }

            // Process and write any remaining data to the output file
            if (batchCounter > 0) {
                String processedBatch = batchData.toString().toUpperCase();
                writer.write(processedBatch);
                writer.flush();
            }

            System.out.println("Batch processing completed successfully!");

        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
        // Measure end time
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Measure memory after processing
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = afterMemory - beforeMemory;

        // Display runtime and memory consumption
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        System.out.println("Memory used: " + memoryUsed / (1024 * 1024) + " MB");
    }
}
