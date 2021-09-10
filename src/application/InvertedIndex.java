package application;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {
    //TODO: add hash map instance variables for posting index and Document index

    public InvertedIndex (){
    }

    public void createIndex(List<File> filesList) {

        for (File file : filesList) {
            System.out.println("Name:" + file.getName());
            System.out.println(this.getDocWordList(file).toString());

        }
    }

    // Method receives a file as in put and produces a list of words
    // punctuation is removed, words are changed to lower case, and stop words removed from file
    public List<String> getDocWordList(File file) {
        // Place holder for know
        List<String> wordList = new ArrayList<>();

        // TODO: Remove and create a map of stop words outside of this class
        // for testing purposed only
        Map<String, String> tempMap= new HashMap<>();
        tempMap.putIfAbsent("a", "a");

        try {
            // read file line by line
            Files.lines(Paths.get(file.toString()))
                    // remove punctuation and convert each line to lowercase, then split words
                    .map(line -> line.replaceAll("\\p{Punct}", "").toLowerCase().split(" "))
                    // add words to word list if they are not stop words or empty strings
                    .forEach(words -> {
                        for (String word : words) {
                            if (!tempMap.containsKey(word) && !word.isEmpty()) {
                                wordList.add(word);
                            }
                        }
                    });

        } catch (Exception e) {
            System.out.println("Error:\n" + e.getMessage());
        }

        return wordList;
    }
} // end of InvertedIndex class
