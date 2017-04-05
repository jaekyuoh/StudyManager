package edu.gatech.seclass.studymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WordsListActivity extends AppCompatActivity {

    ArrayList<String> currentListNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        ButterKnife.bind(this);

    }




    @OnClick(R.id.add_word_fab)
    public void onAddWord(View v) {
        Intent i = new Intent(getApplicationContext(), AddWordActivity.class);
        startActivity(i);
    }
}
