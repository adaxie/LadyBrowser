package com.mobile.ladybrowser.titlebar;

import com.mobile.ladybrowser.BaseUi;
import com.mobile.ladybrowser.R;
import com.mobile.ladybrowser.R.id;
import com.mobile.ladybrowser.R.layout;
import com.mobile.ladybrowser.view.PageProgressView;
import com.mobile.ladybrowser.view.UrlInputView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class TitleBar extends RelativeLayout{

    private static final int PROGRESS_MAX = 100;

    private PageProgressView mProgress;
    private UrlInputView mUrlInput;
    private ImageView mFavicon;
    private ImageView mStop;
    private ImageView mRefresh;
    private ImageView mVoice;
    private ImageView mClear;

    private BaseUi mBaseUi;

    //state
    private boolean mIsFixedTitleBar;
    private boolean mInLoad;

    public TitleBar(Context context, BaseUi ui) {
        super(context, null);
        mBaseUi = ui;
        initLayout(context);
        setTitleBar();
    }

    private void initLayout(Context context) {
        LayoutInflater factory = LayoutInflater.from(context);
        factory.inflate(R.layout.title_bar, this);
        mFavicon = (ImageView)findViewById(R.id.favicon);
        mStop = (ImageView)findViewById(R.id.stop);
        mUrlInput = (UrlInputView)findViewById(R.id.url);
        //mRefresh = (ImageView)findViewById(R.id.refresh);
        mVoice = (ImageView)findViewById(R.id.voice);
        mClear = (ImageView)findViewById(R.id.clear);
        mProgress = (PageProgressView) findViewById(R.id.progress);
    }

    public PageProgressView getProgressView() {
        return mProgress;
    }

    private void setTitleBar() {
        mBaseUi.addTitleBar(this);
    }

   //Update the progress, from 0 to 100;
    public void setProgress(int newProgress) {
        if (newProgress >= PROGRESS_MAX) {
            
        }
    }

}