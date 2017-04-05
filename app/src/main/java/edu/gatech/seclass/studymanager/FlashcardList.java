package edu.gatech.seclass.studymanager;

import java.util.ArrayList;

/**
 * Created by jaekyuoh on 2017. 4. 5..
 */

public class FlashcardList {
    String listName;
    ArrayList<Flashcard>  flashcardList = new ArrayList<Flashcard>();

    public FlashcardList(String listName, ArrayList<Flashcard> flashcardList) {
        this.listName = listName;
        this.flashcardList = flashcardList;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<Flashcard> getFlashcardList() {
        return flashcardList;
    }

    public void setFlashcardList(ArrayList<Flashcard> flashcardList) {
        this.flashcardList = flashcardList;
    }
}
