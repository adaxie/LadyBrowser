package com.mobile.ladybrowser;

import android.content.Context;
import android.webkit.WebView;

public class Tab {
    
    private String TAG = "LadyBrowser-Tab";

    final private Controller mController;
    final private WebView mWebView;
    
    public Tab(WebView w, Controller controller) {
        mWebView = w;
        mController = controller;
    }
    
    public WebView getWebView() {
        return mWebView;
    }
    
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }
}