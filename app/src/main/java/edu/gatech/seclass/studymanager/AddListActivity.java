package edu.gatech.seclass.studymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.seclass.studymanager.db.DatabaseHelper;
import edu.gatech.seclass.studymanager.models.Flashcard;
import edu.gatech.seclass.studymanager.models.FlashcardList;
import edu.gatech.seclass.studymanager.util.ShowMessage;
import edu.gatech.seclass.studymanager.util.StringListConverter;

public class AddListActivity extends AppCompatActivity {

    @BindView(R.id.list_name_editText) EditText listNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.add_list_btn)
    public void onAddList(View v) {
        String listName = listNameText.getText().toString();

        if((listName.equals(""))){
            ShowMessage.show(this,"Not Enough Information","List name cannot be empty");
            listNameText.setText("");
        }
        else{
            // Check if id is not in customer list, give id to customer else compute again for random and unique ID
            if (DatabaseHelper.getInstance(this).validListName(listName)) {
                ArrayList<Flashcard> list = new ArrayList<Flashcard>();
                Flashcard card = new Flashcard("a","b");
                list.add(card);
                FlashcardList flashcardList = new FlashcardList(listName, list);// should convert ArrayList<Flashcard> or FlashcardList to string format
                ArrayList<FlashcardList> listToBeSaved = new ArrayList<>();
                listToBeSaved.add(flashcardList);
                String word_a = listToBeSaved.get(0).getFlashcardList().get(0).getWord_a();
                String word_b = listToBeSaved.get(0).getFlashcardList().get(0).getWord_b();
                Toast.makeText(getApplicationContext(), word_a + " and " + word_b, Toast.LENGTH_LONG).show();
//                ArrayList<FlashcardList> list = StringListConverter.StringToList(contents,clickedListName);

                String content = "";
                try {
                    content = StringListConverter.ListToString(listToBeSaved,listName);
                    Toast.makeText(getApplicationContext(), content + " has been added as " +
                            listName + " content.", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                DatabaseHelper.getInstance(this).insertData(listName,content,0);

                ShowMessage.show(this,"Success","Flashcard List has been successfully added!");
                listNameText.setText("");
            }
            else{
                ShowMessage.show(this,"Duplicate List Name","You already have same list name! Please choose different list name.");
                //listNameText.setText("");
            }
        }
    }
}
