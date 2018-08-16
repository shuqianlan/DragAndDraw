package com.smartlocation;

import android.app.Application;
import android.content.Context;

import com.smartlocation.comp.settings.SettingsHelper;

public class LSApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        SettingsHelper.initialize();
    }

    public static Context getContext() {
        return sContext;
    }
}
