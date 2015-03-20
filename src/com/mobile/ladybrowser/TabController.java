package com.mobile.ladybrowser;



import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class TabController {
    private static String TAG = "LadyBrowser-TabController";

    private Context mContext;
    private Controller mController;
    private List<Tab> tabs;
    private int mCurrentTab = -1;

    public TabController(Context ctx, Controller controller) {
        mContext = ctx;
        mController = controller;
        tabs = new ArrayList<Tab>();
    }

    public void addNewTab(Tab tab) {
        if (tabs.contains(tab)) {
            return;
        }
        tabs.add(tab);
    }

    public void setActiveTab(Tab tab) {
        if (tabs.contains(tab)) {
            mCurrentTab = tabs.indexOf(tab);
        }
    }

    public Tab getActiveTab() {
        Tab tab = null;
        try {
            tab = tabs.get(mCurrentTab);
        } catch (IndexOutOfBoundsException ex) {
            Log.e(TAG, "getActiveTab error: " + ex);
            return null;
        }
        return tab;
    }
}