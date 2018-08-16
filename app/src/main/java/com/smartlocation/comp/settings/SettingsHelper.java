package com.smartlocation.comp.settings;

import android.content.Context;

import com.smartlocation.LSApplication;
import com.smartlocation.comp.draganddraw.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsHelper {

    private static final List<Holder> sSettingItems = new ArrayList<>();
    private static Context context = LSApplication.getContext();

    public static void initialize() {
        sSettingItems.add(new Holder("wifi", context.getString(R.string.settings_wifi)));
        sSettingItems.add(new Holder("volume", context.getString(R.string.settings_volume)));
        sSettingItems.add(new Holder("about", context.getString(R.string.settings_about)));
        sSettingItems.add(new Holder("version", context.getString(R.string.settings_version)));
        sSettingItems.add(new Holder("os", context.getString(R.string.settings_os)));
        sSettingItems.add(new Holder("group1", context.getString(R.string.settings_group1)));
        sSettingItems.add(new Holder("group2", context.getString(R.string.settings_group2)));
    }

    public static List<Holder> getSettingItemsList() {
        return sSettingItems;
    }

    public static class Holder {
        public String mTitle;
        public String mId;

        public Holder(String id, String title) {
            mTitle = title;
            mId    = id;
        }
    }
}
