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
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class TitleBar extends RelativeLayout{

    private static final int PROGRESS_MAX = 100;

    private PageProgressView mProgress;
    private UrlInputView mUrlInput;



    //state
    private boolean mIsFixedTitleBar;
    private boolean mInLoad;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attr) {
        super(context, null);
    }

    public TitleBar(Context ctx, AttributeSet attr, int defStyle) {
        super(ctx, attr, defStyle);
    }

    public PageProgressView getProgressView() {
        return mProgress;
    }

   //Update the progress, from 0 to 100;
    public void setProgress(int newProgress) {
        if (newProgress >= PROGRESS_MAX) {
            
        }
    }

}