package edu.gatech.seclass.studymanager;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddListActivity extends AppCompatActivity {
    DBManager myDb;

    @BindView(R.id.list_name_editText) EditText listNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        ButterKnife.bind(this);
        myDb = new DBManager(this);
    }
    @OnClick(R.id.add_list_btn)
    public void onAddList(View v) {
        String listName = listNameText.getText().toString();

        if((listName.equals(""))){
            showMessage("Not Enough Information","List name cannot be empty");
            listNameText.setText("");
        }
        else{
            // Check if id is not in customer list, give id to customer else compute again for random and unique ID
            if (myDb.validListName(listName)) {
                ArrayList<Flashcard> list = new ArrayList<Flashcard>();
                String content = ""; // should convert ArrayList<Flashcard> or FlashcardList to string format
                FlashcardList flashcardList = new FlashcardList(listName, list);// should convert ArrayList<Flashcard> or FlashcardList to string format
                myDb.insertData(listName,content,0);
            }
            else{
                showMessage("Duplicate List Name","You already have same list name! Please choose different list name.");
                //listNameText.setText("");
            }
        }
    }



    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
