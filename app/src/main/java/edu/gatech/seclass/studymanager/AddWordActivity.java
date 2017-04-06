package edu.gatech.seclass.studymanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.seclass.studymanager.db.DatabaseHelper;
import edu.gatech.seclass.studymanager.models.Flashcard;
import edu.gatech.seclass.studymanager.models.FlashcardList;
import edu.gatech.seclass.studymanager.util.ShowMessage;
import edu.gatech.seclass.studymanager.util.StringListConverter;

public class AddWordActivity extends AppCompatActivity {
    @BindView(R.id.word_front_editText) EditText frontText;
    @BindView(R.id.word_back_editText) EditText backText;
    String clickedListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        ButterKnife.bind(this);

        Intent i = getIntent();
        clickedListName = i.getStringExtra("address");


    }


    @OnClick(R.id.add_word_btn)
    public void onAddWord(View v){
        String front = frontText.getText().toString();
        String back = backText.getText().toString();

        if((front.equals("") || back.equals(""))){
            ShowMessage.show(this,"Not Enough Information","List name cannot be empty");
            frontText.setText("");
            backText.setText("");
        }
        else{
            //TODO: FINISH ADDING WORD
            String contents = DatabaseHelper.getInstance(this).getContents(clickedListName);

            ArrayList<FlashcardList> list = null;
            try {
                list = StringListConverter.StringToList(contents,clickedListName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            FlashcardList flashcardList = list.get(0);
            flashcardList.getListName(); // should be same as clickedListName
            ArrayList<Flashcard> flashcards = flashcardList.getFlashcardList();
            Flashcard newFlashCard = new Flashcard(front,back);
            flashcards.add(newFlashCard);

            FlashcardList flashcardListToBeSaved = new FlashcardList(clickedListName, flashcards);// should convert ArrayList<Flashcard> or FlashcardList to string format
            ArrayList<FlashcardList> listToBeSaved = new ArrayList<>();
            listToBeSaved.add(flashcardListToBeSaved);

            String updatedContents = null;
            try {
                updatedContents = StringListConverter.ListToString(listToBeSaved,clickedListName);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            DatabaseHelper.getInstance(this).insertData(clickedListName,updatedContents,flashcards.size());
        }
    }
}
