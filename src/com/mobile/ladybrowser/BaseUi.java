package com.mobile.ladybrowser;

import com.mobile.ladybrowser.titlebar.TitleBar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class BaseUi {
    private static final String LOGTAG = "BaseUi";

    private FrameLayout.LayoutParams COVER_SCREEN_PARAM = new FrameLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT);

    private Activity mActivity;
    private Controller mController;
    private FrameLayout mContentView;
    private FrameLayout mFixedTitleBarContainer;
    private TitleBar mTitleBar;
    private FrameLayout mToolBar;
    
    public BaseUi(Activity browser, Controller controller) {
        mActivity = browser;
        mController = controller;
        FrameLayout frameLayout = (FrameLayout)browser.getWindow().getDecorView().findViewById(android.R.id.content);
        LayoutInflater.from(browser).inflate(R.layout.custom_screen, frameLayout);
        
        mFixedTitleBarContainer = (FrameLayout) frameLayout.findViewById(R.id.fixed_titlebar_container);
        mContentView = (FrameLayout)frameLayout.findViewById(R.id.main_content);
        mToolBar = (FrameLayout)frameLayout.findViewById(R.id.tool_bar);
        mTitleBar = new TitleBar(browser);
        attachTitleBar();
    }

    private void attachTitleBar() {
        mFixedTitleBarContainer.addView(mTitleBar.getTitleBarView());
    }
    
    private void attachTabToContentView(Tab tab) {
        if (tab == null || tab.getWebView() == null) {
            return;
        }
        WebView webview = tab.getWebView();
        ViewGroup parent = (ViewGroup)webview.getParent();
        if (parent != null && parent != mContentView) {
            parent.removeView(webview);
        }
        mContentView.addView(webview);
    }
    
    private void removeTabFromContentView(Tab tab) {
        if (tab == null || tab.getWebView() == null) {
            return;
        }
        WebView webview = tab.getWebView();
        ViewGroup parent = (ViewGroup)webview.getParent();
        if (mContentView == parent) {
            mContentView.removeView(webview);
        }
    }
    
    public void attachTab(Tab tab) {
        attachTabToContentView(tab);
    }
    
    public void removeTab(Tab tab) {
        removeTabFromContentView(tab);
    }
}