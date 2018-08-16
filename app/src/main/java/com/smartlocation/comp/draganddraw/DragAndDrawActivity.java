package com.smartlocation.comp.draganddraw;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DragAndDrawActivity extends BaseActivity {

    public static final String TAG ="DragAndDrawActivity";

    @Override
    protected Fragment creatFragmentInstance() {
        return DragAndDrawFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, DragAndDrawActivity.class);

        return i;
    }

    @Override
    protected void onResume() {
        super.onResume();

        String root = "/ep/27:48/m";
        String pattern = "/(ep|ai|io|agt|fav)/(.*)(/m)?(/?)(.*)?";
        Matcher m = Pattern.compile(pattern).matcher(root);
        if (m.find()) {
            Log.d(TAG, "onResume: 1 " + m.group(1));
            Log.d(TAG, "onResume: 2 " + m.group(2));
            Log.d(TAG, "onResume: 3 " + m.group(3));
            Log.d(TAG, "onResume: 4 " + m.group(4));
        }

        String fav = "lm/m2m/7504552/fav/ugrp16";

        Matcher m1 = Pattern.compile(".*/fav/(.*)").matcher(fav);
        if (m1.find()) {
            Log.d(TAG, "onResume: 1 " + m1.group(1));
        }

    }
}
