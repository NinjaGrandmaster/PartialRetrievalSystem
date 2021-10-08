package application;

import java.util.Map;

public class TfIdfLookup {
    private Map<String, Integer> docIndex;
    private Map<String, PostingUnit> postingIndex;

    public TfIdfLookup(Map<String, Integer> docIndex, Map<String, PostingUnit> postingIndex) {
        this.docIndex = docIndex;
        this.postingIndex = postingIndex;
    }

    public String lookup(String term) {
        // if term is in posting index calculate tdf for all documents it is found in
        // else return No Match
        if (this.postingIndex.containsKey(term)) {
            String outString = "";

            // get a terms postings from posting index
            Map<String, Integer> termPostings = this.postingIndex.get(term).getPostingMap();

            // calculate the TF-IDF for all documents containing entered term,
            for (String docId : termPostings.keySet()) {

                double tfIdf = this.calculateTFIDF(
                        termPostings.get(docId), // frequency of term in current document
                        this.docIndex.getOrDefault(docId, 0), // total words in current document
                        this.docIndex.size(), // number of documents in the collection
                        termPostings.size()); // number of documents the inputted term is found in

                outString += docId + " - " + tfIdf + "\n";
            }

            return outString;
        }

        return "No Match";
    }

    private double calculateTFIDF(int termFreq, int wordsInDoc, int docsInCollection, int termDocAppearances) {
        // TODO: check accuracy of method
        double tf = (double) termFreq / (double) wordsInDoc;

        double idf = 1 + (Math.log((double) docsInCollection / (double) (termDocAppearances + 1)) / Math.log(2));

        return tf * idf;
    }
}
