package com.mobile.ladybrowser;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Tab {

    private String TAG = "LadyBrowser-Tab";

    final private Controller mController;
    private WebView mWebView;
    
    public Tab(WebView w, Controller controller) {
        setWebView(w);
        mController = controller;
    }

    final private WebViewClient mWebViewClient = new WebViewClient(){

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    final private WebChromeClient mWebChromeClient = new WebChromeClient() {

    };

    private void setWebView(WebView w) {
        mWebView = w;
        w.setWebChromeClient(mWebChromeClient);
        w.setWebViewClient(mWebViewClient);
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }
}