package edu.gatech.seclass.studymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.to_flashcard_list_btn)
    public void onToFlashcardListBtnClick(View v) {
        Intent i = new Intent(getApplicationContext(), FlashcardListActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.to_alarm_btn)
    public void onToAlarmBtnClick(View v) {
        Intent i = new Intent(getApplicationContext(), FlashcardListActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.to_calendar_btn)
    public void onToCanlendarBtnClick(View v) {
        Intent i = new Intent(getApplicationContext(), FlashcardListActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.to_quiz_btn)
    public void onToQuizBtnClick(View v) {
        Intent i = new Intent(getApplicationContext(), FlashcardListActivity.class);
        startActivity(i);
    }
}
