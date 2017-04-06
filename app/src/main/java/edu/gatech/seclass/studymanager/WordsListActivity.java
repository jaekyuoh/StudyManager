package edu.gatech.seclass.studymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.seclass.studymanager.adapters.FlashcardListRecyclerAdapter;
import edu.gatech.seclass.studymanager.adapters.WordsListRecyclerAdapter;
import edu.gatech.seclass.studymanager.db.DatabaseHelper;
import edu.gatech.seclass.studymanager.util.StringListConverter;

public class WordsListActivity extends AppCompatActivity {

    ArrayList<String> currentFrontList;
    ArrayList<String> currentBackList;
    Context mContext;
    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;

    String clickedListName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        ButterKnife.bind(this);

        Intent i = getIntent();
        clickedListName = i.getStringExtra("listName");



        mContext = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.word_list_recyclerview);

        String contentsA = DatabaseHelper.getInstance(this).getContentsA(clickedListName);
        String contentsB = DatabaseHelper.getInstance(this).getContentsB(clickedListName);

        try {
            currentFrontList = StringListConverter.StringToList(contentsA,clickedListName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            currentBackList = StringListConverter.StringToList(contentsB,clickedListName);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (currentFrontList!=null) {
            Adapter = new WordsListRecyclerAdapter(mContext, currentFrontList, currentBackList, new WordsListRecyclerAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(String str) {
                    //TODO: pass str as a key
//                    Toast.makeText(getApplicationContext(), str + " clicked ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), WordsListActivity.class);
                    i.putExtra("listName", str);
                    startActivity(i);
                }
            });
            recyclerView.setAdapter(Adapter);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ButterKnife.bind(this);

        Intent i = getIntent();
        clickedListName = i.getStringExtra("listName");



        mContext = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.word_list_recyclerview);

        String contentsA = DatabaseHelper.getInstance(this).getContentsA(clickedListName);
        String contentsB = DatabaseHelper.getInstance(this).getContentsB(clickedListName);

        try {
            currentFrontList = StringListConverter.StringToList(contentsA,clickedListName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            currentBackList = StringListConverter.StringToList(contentsB,clickedListName);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (currentFrontList!=null) {
            Adapter = new WordsListRecyclerAdapter(mContext, currentFrontList, currentBackList, new WordsListRecyclerAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(String str) {
                    //TODO: Navigate to detailed word activity
                    Toast.makeText(getApplicationContext(), str + " clicked and Should be navigated to detailed version of word",
                            Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(getApplicationContext(), WordsListActivity.class);
//                    i.putExtra("listName", str);
//                    startActivity(i);
                }
            });
            recyclerView.setAdapter(Adapter);
        }

    }



    @OnClick(R.id.add_word_fab)
    public void onAddWord(View v) {
        Intent i = new Intent(getApplicationContext(), AddWordActivity.class);
        i.putExtra("listName", clickedListName);
        startActivity(i);
    }
}
