package com.mobile.ladybrowser;

import android.app.Activity;


public class Controller {

    private Activity mActivity;
    private BaseUi mUi;
    
    public Controller(Activity browser) {
        mActivity = browser;
        
    }
    
    public void setUi(BaseUi ui) {
        mUi = ui;
    }
}