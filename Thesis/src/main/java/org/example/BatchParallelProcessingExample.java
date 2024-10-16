package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchParallelProcessingExample {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\evaluation_ids.csv";   // Path to the input file
        String outputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\output.txt"; // Path to the output file
        int batchSize = 32000;
        int numberOfThreads = 8;
        int batchCounter = 0;
        int linesPerThread = batchSize / numberOfThreads;

        List<String> lines = new ArrayList<>();

        // Measure start time
        long startTime = System.currentTimeMillis();

        // Measure memory before processing
        Runtime runtime = Runtime.getRuntime();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        // Read lines from the input file
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

        // Create a thread pool with the specified number of threads
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        // Split the batch into chunks for each thread to process
        for (int i = 0; i < numberOfThreads; i++) {
            final int start = i * linesPerThread;
            final int end = Math.min(start + linesPerThread, lines.size()); // Handle the case where the file size isn't a perfect multiple of batchSize
            executor.submit(() -> processLines(lines, start, end, outputFilePath));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
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

    private static void processLines(List<String> lines, int start, int end, String outputFilePath) {
        List<String> processedLines = new ArrayList<>();
        for (int i = start; i < end; i++) {
            // Simulate data processing (e.g., convert to uppercase)
            String processedLine = lines.get(i).toUpperCase();
            processedLines.add(processedLine);
        }

        // Write the processed lines to the output file
        synchronized (BatchParallelProcessingExample.class) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
                for (String line : processedLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

