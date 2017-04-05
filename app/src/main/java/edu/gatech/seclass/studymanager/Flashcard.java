package edu.gatech.seclass.studymanager;

/**
 * Created by jaekyuoh on 2017. 4. 1..
 */

// Base unit for flashcard  (word_a = word_b)
public class Flashcard {
    String word_a;
    String word_b;


    public Flashcard(String word_a, String word_b) {
        this.word_a = word_a;
        this.word_b = word_b;
    }

    public String getWord_a() {
        return word_a;
    }

    public void setWord_a(String word_a) {
        this.word_a = word_a;
    }

    public String getWord_b() {
        return word_b;
    }

    public void setWord_b(String word_b) {
        this.word_b = word_b;
    }
}
