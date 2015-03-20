package com.mobile.ladybrowser.titlebar;

import com.mobile.ladybrowser.BaseUi;
import com.mobile.ladybrowser.Controller;
import com.mobile.ladybrowser.R;
import com.mobile.ladybrowser.R.id;
import com.mobile.ladybrowser.R.layout;
import com.mobile.ladybrowser.Tab;
import com.mobile.ladybrowser.view.PageProgressView;
import com.mobile.ladybrowser.view.UrlInputView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


public class TitleBar implements OnEditorActionListener{

    private Context mContext;
    private Controller mController;
    
    private RelativeLayout mTitleBarView;
    private UrlInputView mUrlInputView;
    private PageProgressView mProgress;

    public TitleBar(Context ctx, Controller controller) {
        mContext = ctx;
        mController = controller;
        mTitleBarView = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.title_bar, null);
        mUrlInputView = (UrlInputView)mTitleBarView.findViewById(R.id.urlinput);
        mUrlInputView.setOnEditorActionListener(this);
        mProgress = (PageProgressView)mTitleBarView.findViewById(R.id.progressbar);
    }
    
    public View getTitleBarView() {
        return mTitleBarView;
    }

    public void setDisplayUrlTitle(Tab tab) {
        String url = tab.getUrl();
        String currentUrl = mUrlInputView.getText().toString();
        if (currentUrl != url) {
            mUrlInputView.setText(url);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            String url = v.getText().toString();
            mController.loadUrl(url);
        }
        return true;
    }
}