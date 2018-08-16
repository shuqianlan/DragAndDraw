package com.smartlocation.comp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smartlocation.comp.draganddraw.BaseActivity;
import com.smartlocation.comp.draganddraw.R;
import com.smartlocation.comp.fragment.ControlFragment;
import com.smartlocation.comp.fragment.InfoFragment;

public class LauncherActivity extends BaseTwoActivity {

    @Override
    protected Fragment createLeftFragment() {
        return InfoFragment.newInstance();
    }

    @Override
    protected Fragment createRightFragment() {
        return ControlFragment.newInstance();
    }

}
