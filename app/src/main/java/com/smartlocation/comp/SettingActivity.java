package com.smartlocation.comp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smartlocation.comp.fragment.SettingsFragment;

public class SettingActivity extends BaseTwoActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SettingActivity.class);
    }

    @Override
    protected Fragment createLeftFragment() {
        return SettingsFragment.newInstance();
    }

    @Override
    protected Fragment createRightFragment() {
        return super.createRightFragment();
    }
}
