package edu.gatech.seclass.studymanager.util;

import android.util.Log;

/**
 * Created by jaekyuoh on 2017. 4. 6..
 */

public class Logger {
    private static final String TAG = "StudyManager";

    private static boolean isDebug() {
        return true;
    }


    public static void d(String tag, String msg) {
        if(isDebug()) {
            Log.d(TAG, tag + " " + msg);
        }
    }

    public static void e(String tag, String msg) {
        if(isDebug()) {
            Log.e(TAG, tag + " " + msg);
        }
    }
}
