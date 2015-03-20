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
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            syncState(view);
            mController.getWebViewController().onPageStarted(Tab.this, view);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            syncState(view);
            mController.getWebViewController().onPageFinished(Tab.this, view);
        }

    };

    final private WebChromeClient mWebChromeClient = new WebChromeClient() {

    };

    private void setWebView(WebView w) {
        mWebView = w;
        w.setWebChromeClient(mWebChromeClient);
        w.setWebViewClient(mWebViewClient);
    }

    private void syncState(WebView w) {
        mCurrentState.mUrl = w.getUrl();
        mCurrentState.mTitle = w.getTitle();
        mCurrentState.mOriginalUrl = w.getOriginalUrl();
        mCurrentState.mFavicon = w.getFavicon();
        mCurrentState.mProgress = w.getProgress();
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void loadUrl(String url) {
        mCurrentState.mUrl = url;
        mWebView.loadUrl(url);
    }

    public String getUrl() {
        return mCurrentState.mUrl;
    }

    private static class PageState {
        private String mUrl;
        private String mOriginalUrl;
        private String mTitle;
        private int mProgress;
        private Bitmap mFavicon;

        PageState(String url)  {
            mUrl = mOriginalUrl = url;
        }
    }
}