package edu.phystech.hw2.analyzer;


import java.util.List;

public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private static final List<String> NEGATIVE_SMILES = List.of(":(", "=(", ":|");

    public NegativeTextAnalyzer() {
        super(NEGATIVE_SMILES, Label.NEGATIVE);
    }
     @Override
    public Label processText(String text) {
        for (String keyword : NEGATIVE_SMILES) {
            if (text.contains(" " + keyword + " ") || text.startsWith(keyword + " ") ||  text.endsWith(" " + keyword)) { 
                return Label.NEGATIVE;
            }
        }
        return Label.OK;
    }
    
}

