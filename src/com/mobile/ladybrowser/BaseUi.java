package com.mobile.ladybrowser;

import com.mobile.ladybrowser.titlebar.TitleBar;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BaseUi {
    private static final String LOGTAG = "BaseUi";

    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS =
            new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

    protected static final FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = 
            new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER);

    private Activity mActivity;
    private Controller mController;
    private FrameLayout mContentView;
    private FrameLayout mFixedTitlebarContainer;
    private TitleBar mTitleBar;
    private FrameLayout mToolBar;
    
    public BaseUi(Activity browser, Controller controller) {
        mActivity = browser;
        mController = controller;
        FrameLayout frameLayout = (FrameLayout) browser.getWindow()
                .getDecorView().findViewById(android.R.id.content);
        LayoutInflater.from(browser)
                .inflate(R.layout.custom_screen, frameLayout);
        mFixedTitlebarContainer = (FrameLayout)frameLayout.findViewById(R.id.fixed_titlebar_container);
        mContentView = (FrameLayout)frameLayout.findViewById(R.id.main_content);
        mToolBar = (FrameLayout)frameLayout.findViewById(R.id.tool_bar);
    }
    
    public void addTitleBar(View view) {
        mFixedTitlebarContainer.addView(view);
    }
}