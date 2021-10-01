package application;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {
    //TODO: add hash map instance variable for posting index
    Map<String, String> stopWordsMap; // map of stop words that should not be included in the inverted index
    Map<String, Integer> documentIndex; // map contains number of terms in a document after removing stop words

    public InvertedIndex() {
        this.stopWordsMap = new HashMap<>();
        this.documentIndex = new HashMap<>();
    }

    public InvertedIndex (Map<String, String> stopWordsMap){
        // if map is null create a new hash map object
        if (stopWordsMap == null) {
            this.stopWordsMap = new HashMap<>();
        } else {
            this.stopWordsMap = stopWordsMap;
        }

        this.documentIndex = new HashMap<>();
    }

    public void createIndex(List<File> filesList) {
        //TODO: implement code to generate posting index
        for (File file : filesList) {

            List<String> wordList = this.getDocWordList(file);
            
            documentIndex.putIfAbsent(file.getName(), wordList.size());
        }
    }

    // Method receives a file as in put and produces a list of words
    // punctuation is removed, words are changed to lower case, and stop words removed from file
    public List<String> getDocWordList(File file) {
        // List to hold word in a file
        List<String> wordList = new ArrayList<>();

        try {
            // read file line by line
            Files.lines(Paths.get(file.toString()))
                    // remove punctuation and convert each line to lowercase, then split words
                    .map(line -> line.replaceAll("\\p{Punct}", "").toLowerCase().split(" "))
                    // add words to word list if they are not stop words or empty strings
                    .forEach(words -> {
                        for (String word : words) {
                            if (!this.stopWordsMap.containsKey(word) && !word.isEmpty()) {
                                wordList.add(word);
                            }
                        }
                    });
        } catch (Exception e) {
            System.out.println("Error:\n" + e.getMessage());
        }

        return wordList;
    }

    public Map<String, Integer> getDocumentIndex() {
        return this.documentIndex;
    }

    // Method counts the frequency of a word in a list assuming the list in unsorted
    // Params: target word and a list of stings
    // Output: integer representing frequency of target word in provided list
    private int countWordFreq(String targetWord, List<String> wordList) {
        int count = 0;

        if (!wordList.isEmpty()) {
            for(String word : wordList) {
                if (word.equals(targetWord)) {
                    count += 1;
                }
            }
        }

        return count;
    }
} // end of InvertedIndex class
