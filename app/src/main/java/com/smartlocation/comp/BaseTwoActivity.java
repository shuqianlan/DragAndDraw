package com.smartlocation.comp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.smartlocation.comp.draganddraw.R;

public class BaseTwoActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_left);

        if (fragment == null) {
            fragment = createLeftFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_left, fragment)
                    .commit();
        }

        Fragment fragment2 = fm.findFragmentById(R.id.fragment_right);

        if (fragment2 == null) {
            fragment2 = createRightFragment();
            if (fragment2 == null) {
                return;
            }
            fm.beginTransaction()
                    .replace(R.id.fragment_right, fragment2)
                    .commit();
        }

    }

    protected Fragment createLeftFragment() {
        return null;
    }

    protected Fragment createRightFragment() {
        return null;
    }

}
