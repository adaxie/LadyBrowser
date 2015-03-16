package com.mobile.ladybrowser;

import android.app.Activity;
import android.webkit.WebView;


public class Controller {

    private Activity mActivity;
    private BaseUi mUi;
    private WebViewController mWebViewController;

    public Controller(Activity browser) {
        mActivity = browser;
        mWebViewController = new WebViewController();
    }
    
    public void setUi(BaseUi ui) {
        mUi = ui;
    }
    
    private Tab createNewTab(boolean incognito) {
        WebView wb = mWebViewController.createBrowserWebView(mActivity, incognito);
        Tab tab = new Tab(wb, this);
        return tab;
    }

    public Tab openTabToHomePage(String url, boolean incognito) {
        Tab tab = createNewTab(incognito);
        if (tab != null && url != null) {
            tab.loadUrl(url);
        }
        return tab;
    }

    public void start() {
        Tab tab = openTabToHomePage("http://www.baidu.com", false);
        mUi.attachTab(tab);
    }
}