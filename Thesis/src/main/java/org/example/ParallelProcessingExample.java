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
import java.util.concurrent.TimeUnit;

public class ParallelProcessingExample {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\evaluation_ids.csv";   // Path to the input file
        String outputFilePath = "C:\\Users\\ferid\\Downloads\\Thesis\\src\\main\\java\\org\\example\\output.txt"; // Path to the output file

        int numberOfThreads = 4; // Number of parallel threads

        // Measure start time
        long startTime = System.currentTimeMillis();

        // Measure memory before processing
        Runtime runtime = Runtime.getRuntime();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            List<String> lines = new ArrayList<>();
            String line;

            // Read the file line by line and distribute the work across threads
            while ((line = reader.readLine()) != null) {
                lines.add(line);

                // When the chunk size is equal to the number of threads, submit the chunk for processing
                if (lines.size() >= numberOfThreads) {
                    executor.submit(new FileProcessorTask(new ArrayList<>(lines), outputFilePath));
                    lines.clear(); // Clear the list for the next chunk
                }
            }

            // Submit the remaining lines (if any) for processing
            if (!lines.isEmpty()) {
                executor.submit(new FileProcessorTask(new ArrayList<>(lines), outputFilePath));
            }

            // Shut down the executor and wait for all tasks to complete
            executor.shutdown();

            System.out.println("Parallel processing completed successfully!");

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

class FileProcessorTask implements Runnable {

    private List<String> lines;
    private String outputFilePath;

    public FileProcessorTask(List<String> lines, String outputFilePath) {
        this.lines = lines;
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            for (String line : lines) {
                // Simulate data manipulation (e.g., convert to lowercase)
                String processedLine = line.toLowerCase();
                writer.write(processedLine);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}

