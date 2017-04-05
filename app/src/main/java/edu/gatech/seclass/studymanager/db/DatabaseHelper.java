package edu.gatech.seclass.studymanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by jaekyuoh on 2017. 4. 1..
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "StudyManager.db";
    public static final int DATABASE_VERSION = 1;
    public static final String FLASHCARD_LIST_TABLE = "flashcard_list_table";

    public static final String COL_1 = "LISTNAME";
    public static final String COL_2 = "CONTENTS";
    public static final String COL_3 = "COUNT";

//    public DBManager(Context context) {
//        super(context, DATABASE_NAME, null, 6);
//        SQLiteDatabase db = this.getWritableDatabase();
//    }


    private static DatabaseHelper sInstance = null;
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FLASHCARD_LIST_TABLE + " (LISTNAME TEXT PRIMARY KEY," +
                "CONTENTS TEXT, COUNT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+FLASHCARD_LIST_TABLE);
        onCreate(db);
    }

    public boolean insertData(String listName,String contents, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,listName);
        contentValues.put(COL_2,contents);
        contentValues.put(COL_3,count);


        long result = db.insert(FLASHCARD_LIST_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + FLASHCARD_LIST_TABLE, null);
        return cursor;
    }

    public String getContents(String listName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select "+COL_2+" from " + FLASHCARD_LIST_TABLE + " where LISTNAME = " + "'" +listName+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() == 0){
            //No Single User
            return null;
        }
        else{
            return cursor.getString(0);
        }
    }

    public boolean updateFlashcardListData(String listName, String contents, int count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,listName);
        contentValues.put(COL_2,contents);
        contentValues.put(COL_3,count);


        //update with ID where id equals to id (given as input) such that matches contentValues supplied
        db.update(FLASHCARD_LIST_TABLE,contentValues, "LISTNAME=?", new String[] {listName});
        return true;
    }

    public Integer deleteData(String listName){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(FLASHCARD_LIST_TABLE,"LISTNAME=?", new String[] {listName});
        return res;
    }


    public ArrayList<String> retrieveAllFlashcardNameList(){
        ArrayList<String> flashcardNameList = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select LISTNAME from " + FLASHCARD_LIST_TABLE, null);
        if(cursor.getCount() == 0){
            //No Single User
            return null;
        }
        else{
            while(cursor.moveToNext()) {
                flashcardNameList.add(cursor.getString(0));
            }
            return flashcardNameList;
        }
    }


    public boolean validListName(String listName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + FLASHCARD_LIST_TABLE + " where LISTNAME = " + "'" +listName+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }



}
