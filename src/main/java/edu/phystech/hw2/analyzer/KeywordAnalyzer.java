package edu.phystech.hw2.analyzer;

import java.util.List;

public abstract class KeywordAnalyzer implements TextAnalyzer {

    private final List<String> keywords;
    private final Label label;

    public KeywordAnalyzer(List<String> keywords, Label label) {
        this.keywords = keywords;
        this.label = label;
    }


    public Label processText(String text) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return label;
            }
        }
        return Label.OK;
    }
}
