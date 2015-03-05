package com.mobile.ladybrowser.view;

import com.mobile.ladybrowser.tool.UrlUtils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * url/search input view handling suggestions
 */
public class UrlInputView extends AutoCompleteTextView{

    public UrlInputView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    public UrlInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    
    public UrlInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }
}