package com.mobile.ladybrowser;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

public class WebViewController {

    private Context mContext;
    private Controller mController;

    public WebViewController(Context ctx, Controller controller) {
        mContext = ctx;
        mController = controller;
    }

    public WebView createBrowserWebView(Context ctx, boolean privateBrowsing) {
        WebView wb = new WebView(ctx, null, android.R.attr.webViewStyle, privateBrowsing);
        initWebViewSettings(wb);
        return wb;
    }
    
    private void initWebViewSettings(WebView w) {
        w.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        w.getSettings().setJavaScriptEnabled(true);
        //w.getSettings().setBu
    }

    public void onPageStarted(Tab tab, WebView webview) {
        mController.getUi().onTabDataChanged(tab);
    }

    public void onPageFinished(Tab tab, WebView webview) {
        mController.getUi().onTabDataChanged(tab);
    }
}