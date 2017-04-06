package edu.gatech.seclass.studymanager.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.studymanager.models.FlashcardList;

/**
 * Created by jaekyuoh on 2017. 4. 6..
 */

public class StringListConverter {

    public static String ListToString(ArrayList<String> list, String listName) throws JSONException {
        //When arraylist --> json --> string
        JSONObject json_ = new JSONObject();
        json_.put(listName + "_list", new JSONArray(list));
        String strToSave = json_.toString();
        return strToSave;
    }

    public static ArrayList<String> StringToList(String contents, String listName) throws JSONException {
        //string --> json --> arraylist
//        ArrayList<FlashcardList> items = new Gson().fromJson(contents, new TypeToken<List<FlashcardList>>(){}.getType());
        ArrayList<String> items = new ArrayList<String>();
        if (contents == null){
            return items;
        }
        JSONObject json = new JSONObject(contents);
        JSONArray jArray = json.optJSONArray(listName + "_list");
        for (int i = 0; i < jArray.length(); i++) {
            items.add(jArray.getString(i));
//            items.add((FlashcardList) jArray.get(i));
        }
        return items;
    }
}
