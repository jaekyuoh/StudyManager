package edu.gatech.seclass.studymanager.util;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by jaekyuoh on 2017. 4. 5..
 */

public class ShowMessage {
    public static void show(Context context, String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
