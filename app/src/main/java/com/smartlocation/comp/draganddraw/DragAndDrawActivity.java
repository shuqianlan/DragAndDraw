package com.smartlocation.comp.draganddraw;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DragAndDrawActivity extends BaseActivity {

    @Override
    protected Fragment creatFragmentInstance() {
        return DragAndDrawFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, DragAndDrawActivity.class);

        return i;
    }
}
