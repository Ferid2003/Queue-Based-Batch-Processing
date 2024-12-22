package org.example.methodologies;

import org.example.Searching_Algorithms.Boyer_Moore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelBatchProcessing {

    public static void main(String[] args) {

        String inputFilePath =  // Path to the input file
        String outputFilePath =  // Path to the output file

        int numberOfThreads = 8; // Number of parallel threads

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            List<String> lines = new ArrayList<>();
            String line;

            // Read the file line by line and distribute the work across threads
            while ((line = reader.readLine()) != null) {
                lines.add(line);

                // Define batch size
                if (lines.size() >= 128000) {
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
                //TwoWayStringMatching.findAllOccurrences("parent",line, writer);
                //KMP_String_Matching.KMPSearch("14",line, writer);
//                int q = 101;
//                Rabin_Karp.search("14",line,q,writer);
                Boyer_Moore.search(line.toCharArray(), "14".toCharArray(),writer);
            }
            writer.flush();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}

