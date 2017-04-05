package edu.gatech.seclass.studymanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.seclass.studymanager.db.DatabaseHelper;
import edu.gatech.seclass.studymanager.models.FlashcardList;
import edu.gatech.seclass.studymanager.util.ShowMessage;

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
    public void onAddWord(View v) {
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
//            ArrayList<FlashcardList> list =

            DatabaseHelper.getInstance(this).insertData(clickedListName,contents,1);
        }
    }
}
