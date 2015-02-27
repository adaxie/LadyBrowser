package com.mobile.ladybrowser.setting;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.WeakHashMap;
import java.util.prefs.Preferences;

import com.mobile.ladybrowser.search.SearchEngine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.webkit.WebSettings;

public class BrowserSettings implements PreferenceKeys{


    private Context mContext;
    private SharedPreferences mPrefs;
    private WeakHashMap<WebSettings, String> mCustomUserAgents;
    private LinkedList<WeakReference<WebSettings>> mManagedSettings;
    private static BrowserSettings sInstance;

    //Cached settings
    private SearchEngine mSearchEngine;
    
    public static void initialize(final Context context) {
        sInstance = new BrowserSettings(context);
    }

    public static BrowserSettings getInstance() {
        return sInstance;
    }

    private BrowserSettings(Context context) {
        mContext = context.getApplicationContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        mCustomUserAgents = new WeakHashMap<WebSettings, String>();
        mManagedSettings = new LinkedList<WeakReference<WebSettings>>();
    }
    
    public SearchEngine getSearchEngine() {
        if (mSearchEngine == null) {
            updateSearchEngine(false);
        }
        return mSearchEngine;
    }
    
    private void updateSearchEngine(boolean force) {
        String searchEngineName = getSearchEngineName();
        if (force || mSearchEngine == null ||
                !mSearchEngine.getName().equals(searchEngineName)) {
            
        }
    }
    
    //-----------------------------
    // 
    //-----------------------------
    public String getSearchEngineName() {
        return mPrefs.getString(PREF_SEARCH_ENGINE, SearchEngine.BAIDU);
    }
}
