package edu.phystech.hw2.analyzer;


import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TextAnalyzerTest {

    @Test
    public void tooLongTextTest() {
        TooLongTextAnalyzer tooLongTextAnalyzer = new TooLongTextAnalyzer(3);
        Assertions.assertEquals(Label.TOO_LONG, tooLongTextAnalyzer.processText("123123"));
        Assertions.assertEquals(Label.OK, tooLongTextAnalyzer.processText("12"));
    }

    @Test
    public void spamTextTest() {
        SpamAnalyzer spamAnalyzer = new SpamAnalyzer(List.of("kek", "lol"));
        Assertions.assertEquals(Label.SPAM, spamAnalyzer.processText("kek 123"));
        Assertions.assertEquals(Label.OK, spamAnalyzer.processText("123"));
        Assertions.assertEquals(Label.SPAM, spamAnalyzer.processText("123 lol"));
    }

    @Test
    public void negativeTextTest() {
        NegativeTextAnalyzer negativeTextAnalyzer = new NegativeTextAnalyzer();
        Assertions.assertEquals(Label.NEGATIVE, negativeTextAnalyzer.processText("hello :("));
        Assertions.assertEquals(Label.NEGATIVE, negativeTextAnalyzer.processText(":) =("));
        Assertions.assertEquals(Label.NEGATIVE, negativeTextAnalyzer.processText("))) :|"));
        Assertions.assertEquals(Label.OK, negativeTextAnalyzer.processText("))) :||"));
    }
}
