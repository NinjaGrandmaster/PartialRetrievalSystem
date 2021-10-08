package application;

import java.util.HashMap;
import java.util.Map;

public class PostingUnit {
    private Map<String, Integer> postingMap; // contains the document id and number of times a term appears in that document

    public PostingUnit() {
        this.postingMap = new HashMap<>();
    }

    public PostingUnit (String docID) {
        this.postingMap = new HashMap<>();
        this.incrementFrequency(docID);
    }

    // return number of documents a term has appeared in
    public int getDocumentCount() {
        return this.postingMap.size();
    }

    public Map<String, Integer> getPostingMap() {
        return this.postingMap;
    }

    public void incrementFrequency(String docID) {
        // if the document exists in the posting map get the count for the term associated with it
        // else get default value zero
        int count = this.postingMap.getOrDefault(docID, 0);

        // add or update document term count in the posting map
        this.postingMap.put(docID, count + 1);
    }

    @Override
    public String toString() {
        String outString = this.getDocumentCount() + ", ";

        for (String key : this.getPostingMap().keySet()){
            outString += "(" + key + ", " + this.getPostingMap().get(key) + "), ";
        }

        return outString;
    }
}
