package com.smartlocation.comp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartlocation.comp.SettingActivity;
import com.smartlocation.comp.draganddraw.R;

public class InfoFragment extends Fragment implements View.OnClickListener{


    public static InfoFragment newInstance() {

        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        ((Button)v.findViewById(R.id.info_setting)).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        startActivity(SettingActivity.newIntent(getContext()));
    }
}

