package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a directory");
        String dirName = scanner.nextLine();
        File directory = new File(dirName);
        StopWordGenerator stopWordGenerator = new StopWordGenerator();

        if (directory.isDirectory()) {
            FileRetriever fileRetriever = new FileRetriever(directory);
            InvertedIndex invertedIndex = new InvertedIndex(stopWordGenerator.getStopWordMap());

            invertedIndex.createIndex(fileRetriever.getFileList());

            TfIdfLookup tfIdfLookup = new TfIdfLookup(
                    invertedIndex.getDocumentIndex(),
                    invertedIndex.getPostingIndex()
            );

            while (true) {
                System.out.println("Enter a term:");
                String term = scanner.nextLine().toLowerCase();

                System.out.println(tfIdfLookup.lookup(term));

                System.out.println("Enter another term?");
                System.out.println("Y/y - to continue, anything else to end program");
                String anotherTerm = scanner.nextLine();

                if (!anotherTerm.toLowerCase().equals("y")) {
                    break;
                }
            }


        } else {
            System.out.println("Not a directory");
        }
    }
}
