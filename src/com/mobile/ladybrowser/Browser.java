package com.mobile.ladybrowser;

import android.app.Application;
import android.webkit.CookieSyncManager;

public class Browser extends Application {
    private final static String LOGTAG = "LadyBrowser";

    @Override
    public void onCreate() {
        super.onCreate();
        CookieSyncManager.createInstance(this);
    }

}