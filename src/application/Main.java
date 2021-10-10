package application;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a document directory");
        String dirName = scanner.nextLine();
        File directory = new File(dirName);
        StopWordGenerator stopWordGenerator = new StopWordGenerator();

        if (directory.isDirectory()) {
            FileRetriever fileRetriever = new FileRetriever(directory);
            InvertedIndex invertedIndex = new InvertedIndex(stopWordGenerator.getStopWordMap());

            // create the inverted index
            invertedIndex.createIndex(fileRetriever.getFileList());

            TfIdfLookup tfIdfLookup = new TfIdfLookup(
                    invertedIndex.getDocumentIndex(), // pass document index to look up class
                    invertedIndex.getPostingIndex()   // pass posting index to look up class
            );

            while (true) {
                System.out.println("Enter a term:");
                String term = scanner.nextLine().replaceAll("\\p{Punct}", "").toLowerCase();

                System.out.println(tfIdfLookup.lookup(term));

                System.out.println("Enter another term?");
                System.out.println("Y/y - to continue, anything else to end program");
                String anotherTerm = scanner.nextLine();

                if (!anotherTerm.equalsIgnoreCase("y")) {
                    break;
                }
            }


        } else {
            System.out.println("Not a directory");
        }
    }
}
