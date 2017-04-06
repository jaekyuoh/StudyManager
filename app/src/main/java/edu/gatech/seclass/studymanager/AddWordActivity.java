package edu.gatech.seclass.studymanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        clickedListName = i.getStringExtra("listName");
        Toast.makeText(getApplicationContext(), "From list : " + clickedListName, Toast.LENGTH_LONG).show();


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
            String contentsA = DatabaseHelper.getInstance(this).getContentsA(clickedListName);
            String contentsB = DatabaseHelper.getInstance(this).getContentsB(clickedListName);
            ArrayList<String> list_a = new ArrayList<>();
            ArrayList<String> list_b = new ArrayList<>();
            try {
                list_a = StringListConverter.StringToList(contentsA,clickedListName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                list_b = StringListConverter.StringToList(contentsB,clickedListName);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            list_a.add(front);
            list_b.add(back);

            String content_a = "";
            String content_b = "";
            try {
                content_a = StringListConverter.ListToString(list_a,clickedListName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                content_b = StringListConverter.ListToString(list_b,clickedListName);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            DatabaseHelper.getInstance(this).updateFlashcardListData(clickedListName,content_a,content_b,list_a.size());

            //ONLY FOR TESTING
            Cursor cursor = DatabaseHelper.getInstance(this).getAllData();
            if(cursor.getCount() == 0){
                //Show message
//            showMessage("Error", "Nothing found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(cursor.moveToNext()) {
                buffer.append("listname: " + cursor.getString(0) + "\n");
                buffer.append("content_a: " + cursor.getString(1) + "\n");
                buffer.append("content_b: " + cursor.getString(2) + "\n");
                buffer.append("count: " + cursor.getInt(3) + "\n");
            }
            Toast.makeText(getApplicationContext(), buffer + " .", Toast.LENGTH_LONG).show();

        }
    }
}
