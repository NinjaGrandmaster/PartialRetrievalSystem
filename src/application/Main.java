package application;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a directory");
        String dirName = scanner.nextLine();

        File directory = new File(dirName);

        if (directory.isDirectory()) {
            FileRetriever fileRetriever = new FileRetriever(directory);

            System.out.println(fileRetriever);

        } else {
            System.out.println("Not a directory");
        }
    }
}
