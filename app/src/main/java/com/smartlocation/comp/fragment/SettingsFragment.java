package com.smartlocation.comp.fragment;


import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartlocation.comp.draganddraw.R;
import com.smartlocation.comp.settings.SettingsHelper;

import java.util.List;

public class SettingsFragment extends Fragment {
    public static final String TAG = "SettingsItem";

    private RecyclerView mRecycler;
    private SettingsAdapter mAdapter;
    private MyHolder mSelected;
    private List<SettingsHelper.Holder> mItems;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItems = SettingsHelper.getSettingItemsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mRecycler = v.findViewById(R.id.settings_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter();

        return v;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        public ImageView mIcon;
        public TextView  mTitle;
        public String    mCategory;
        public View      mContainer;

        public MyHolder(View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.settings_item_icon);
            mTitle = itemView.findViewById(R.id.settings_item_title);
            mContainer = itemView.findViewById(R.id.settings_item_container);
            mContainer.setOnTouchListener(this);
        }

        public void onBind(SettingsHelper.Holder item) {
            String title = item.mTitle;
            mCategory = item.mId;
            mTitle.setText(title);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (mSelected != null) {
                    if (mSelected.mCategory.equals(mCategory)) {
                        return true;
                    }
                    mSelected.mContainer.setBackgroundColor(getResources().getColor(R.color.color_white));
                    mSelected.mTitle.setTextColor(Color.BLACK);
                }

                mSelected = this;
                mSelected.mContainer.setBackgroundColor(Color.DKGRAY);
                mSelected.mContainer.setBackgroundColor(Color.WHITE);
                return true;
            }
            return false;
        }
    }

    public class SettingsAdapter extends RecyclerView.Adapter<MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.settings_item, parent, false);

            return new MyHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            SettingsHelper.Holder item = mItems.get(position);
            holder.onBind(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private void setAdapter() {
        mAdapter = new SettingsAdapter();
        mRecycler.setAdapter(mAdapter);
    }
}
