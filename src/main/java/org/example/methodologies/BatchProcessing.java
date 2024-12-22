package org.example.methodologies;

import org.example.Searching_Algorithms.Boyer_Moore;
import org.example.Searching_Algorithms.KMP_String_Matching;
import org.example.Searching_Algorithms.Rabin_Karp;
import org.example.Searching_Algorithms.TwoWayStringMatching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BatchProcessing {


    public static void main(String[] args) {

        String inputFilePath =  // Path to the input file
        String outputFilePath = // Path to the output file

        // Batch size defines how many lines to process in one batch
        int batchSize = 128000;
        int batchCounter = 0;


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
                    KMP_String_Matching.KMPSearch("14", batchData.toString(),writer);
                    //Boyer_Moore.search(batchData.toString().toCharArray(),"14".toCharArray(),writer);
                    //TwoWayStringMatching.findAllOccurrences("parent", batchData.toString(),writer);
                    //int q = 101;
                    //Rabin_Karp.search("14",batchData.toString(),q,writer);
                    writer.flush();
                    batchData.setLength(0); // Clearing the batch data
                    batchCounter = 0;
                }
            }

            // Process and write any remaining data to the output file
            if (batchCounter > 0) {
                //Boyer_Moore.search(batchData.toString().toCharArray(),"14".toCharArray(),writer);
//                int q = 101;
//                Rabin_Karp.search("14",batchData.toString(),q,writer);
                KMP_String_Matching.KMPSearch("14", batchData.toString(),writer);
                //TwoWayStringMatching.findAllOccurrences("parent", batchData.toString(),writer);
                writer.flush();
            }

            System.out.println("Batch processing completed successfully!");

        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }
}
