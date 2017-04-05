package edu.gatech.seclass.studymanager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.security.AccessController.getContext;

public class FlashcardListActivity extends AppCompatActivity {
    DBManager myDb;
    ArrayList<String> currentListNames;

    Context mContext;
    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_list);
        ButterKnife.bind(this);

        myDb = new DBManager(this);
        mContext = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.flashcard_list_recyclerview);
        recyclerView.setHasFixedSize(true);




        currentListNames = myDb.retrieveAllFlashcardNameList();

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        Adapter = new FlashcardListRecyclerAdapter(mContext,currentListNames, new FlashcardListRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(String str) {
                //TODO: pass str as a key
                Intent i = new Intent(getApplicationContext(), WordsListActivity.class);
                startActivity(i);
            }
        });
        recyclerView.setAdapter(Adapter);

//        Toast.makeText(getApplicationContext(), tempName + " has been added.", Toast.LENGTH_LONG).show();
    }




    @OnClick(R.id.add_flashcard_list_fab)
    public void onAddFlashcardList(View v) {
        Intent i = new Intent(getApplicationContext(), AddListActivity.class);
        startActivity(i);
    }





}
