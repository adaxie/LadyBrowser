package com.mobile.ladybrowser;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

public class WebViewController {

    public WebView createBrowserWebView(Context ctx, boolean privateBrowsing) {
        WebView wb = new WebView(ctx, null, android.R.attr.webViewStyle, privateBrowsing);
        initWebViewSettings(wb);
        return wb;
    }
    
    private void initWebViewSettings(WebView w) {
        w.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //w.getSettings().setBu
    }
}