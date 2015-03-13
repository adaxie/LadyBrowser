package com.mobile.ladybrowser.titlebar;

import com.mobile.ladybrowser.BaseUi;
import com.mobile.ladybrowser.R;
import com.mobile.ladybrowser.R.id;
import com.mobile.ladybrowser.R.layout;
import com.mobile.ladybrowser.view.PageProgressView;
import com.mobile.ladybrowser.view.UrlInputView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class TitleBar {

    private Context mContext;
    
    private RelativeLayout mTitleBarView;

    public TitleBar(Context ctx) {
        mContext = ctx;
        mTitleBarView = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.title_bar, null);
    }
    
    public View getTitleBarView() {
        return mTitleBarView;
    }
}