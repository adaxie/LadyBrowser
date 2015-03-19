package com.mobile.ladybrowser;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Tab {

    private String TAG = "LadyBrowser-Tab";

    final private Controller mController;
    private WebView mWebView;
    PageState mCurrentState;
    
    public Tab(WebView w, Controller controller) {
        mController = controller;
        mCurrentState = new PageState("");
        setWebView(w);
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
        mCurrentState.setUrl(url);
        mWebView.loadUrl(url);
    }

    public String getUrl() {
        return mCurrentState.mUrl;
    }

    private static class PageState {
        String mUrl;
        String mOriginalUrl;
        String mTitle;
        int mProgress;
        Bitmap mFavicon;
        
        PageState(String url) {
            mUrl = mOriginalUrl = url;
        }

        void setUrl(String url) {
            mUrl = url;
        }
        
        void setOriginalUrl(String originalUrl) {
            mOriginalUrl = originalUrl;
        }
    }
}