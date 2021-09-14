package application;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class StopWordGenerator {
    private HashMap<String, String> stopWordMap;

    public StopWordGenerator() {
        this.stopWordMap = new HashMap<>();
        this.createStopWordMap();
    }

    private void createStopWordMap() {
        try {
            // read file line by line
            Files.lines(Paths.get("stopwords.txt"))
                    .forEach(word -> this.stopWordMap.putIfAbsent(word, word));

        } catch (Exception e) {
            System.out.println("Error (Class: StopWordGenerator):\n" + e.getMessage());
        }
    }

    public HashMap<String, String> getStopWordMap() {
        return this.stopWordMap;
    }
}
