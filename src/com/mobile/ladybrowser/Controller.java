package com.mobile.ladybrowser;

import android.app.Activity;
import android.webkit.WebView;


public class Controller {

    private Activity mActivity;
    private BaseUi mUi;
    private WebViewController mWebViewController;
    private TabController mTabController;

    public Controller(Activity browser) {
        mActivity = browser;
        mWebViewController = new WebViewController(browser, this);
        mTabController = new TabController(browser, this);
    }
    
    public void setUi(BaseUi ui) {
        mUi = ui;
    }

    public BaseUi getUi() {
        return mUi;
    }

    public WebViewController getWebViewController() {
        return mWebViewController;
    }

    private Tab createNewTab(boolean incognito) {
        WebView wb = mWebViewController.createBrowserWebView(mActivity, incognito);
        Tab tab = new Tab(wb, this);
        return tab;
    }

    private void setActiveTab(Tab tab) {
        mUi.setActiveTab(tab);
        mTabController.setActiveTab(tab);
    }

    public void loadUrl(String url) {
        Tab tab = mTabController.getActiveTab();
        tab.loadUrl(url);
    }

    public Tab openTabToHomePage(String url, boolean incognito) {
        Tab tab = createNewTab(incognito);
        mTabController.addNewTab(tab);
        if (tab != null && url != null) {
            tab.loadUrl(url);
        }
        setActiveTab(tab);
        return tab;
    }

    public void start() {
        Tab tab = openTabToHomePage("http://www.baidu.com", false);
        mUi.attachTab(tab);
    }
}