package application;

import java.io.File;
import java.util.HashMap;
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

        } else {
            System.out.println("Not a directory");
        }
    }
}
