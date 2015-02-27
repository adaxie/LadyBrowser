package com.mobile.ladybrowser.view;

import com.mobile.ladybrowser.tool.UrlUtils;
import com.mobile.ladybrowser.view.SuggestionsAdapter.CompletionListener;

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
public class UrlInputView extends AutoCompleteTextView
        implements OnEditorActionListener,
        CompletionListener, OnItemClickListener, TextWatcher{

    static final String TYPED = "browser-type";
    static final String SUGGESTED = "browser-suggest";
    
    static final int POST_DELAY = 100;
    
    static interface StateListener {
        static final int STATE_NORMAL = 0;
        static final int STATE_HIGHLIGHTED = 1;
        static final int STATE_EDITED = 2;
        
        public void onStateChanged(int state);
    }
    
    interface UrlInputListener {
        public void onDismiss();
        public void onAction(String text, String extra, String source);
        public void onCopySuggestion(String text);
    }

    private UrlInputListener mListener;
    private InputMethodManager mInputManager;
    private SuggestionsAdapter mAdapter;
    private View mContainer;
    private boolean mIncognitoMode;
    private boolean mNeedsUpdate;
    
    private int mState;
    private StateListener mStateLIstener;
    
    public UrlInputView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    public UrlInputView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.autoCompleteTextViewStyle); 
    }
    
    public UrlInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context ctx) {
        mInputManager = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        mAdapter = new SuggestionsAdapter(ctx, this);
        mState = StateListener.STATE_NORMAL;
        mNeedsUpdate = false;
        
        setAdapter(mAdapter);
        setSelectAllOnFocus(true);
        setThreshold(1);
        setOnItemClickListener(this);
        setOnEditorActionListener(this);
        addTextChangedListener(this);
        //setDropDownAnchor();
        
    }
    
    /**onEditorActionListener start***/
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        finishInput(getText().toString(), null, TYPED);
        return true;
    }
    
    private void finishInput(String url, String extra, String source) {
        dismissDropDown();
        mInputManager.hideSoftInputFromWindow(getWindowToken(), 0);
        if (TextUtils.isEmpty(url)) {
            mListener.onDismiss();
        } else {
            if (mIncognitoMode && isSearch(url)) {
                
            }
        }
        
    }
    
    boolean isSearch(String inUrl) {
        String url = UrlUtils.fixUrl(inUrl).trim();
        if (TextUtils.isEmpty(url))
            return false;
       /* if (Patterns.WEB_URL.matcher(url).matches()
                || UrlUtils.A){
            
        }*/
        return false;
    }
    
    /**onEditorActionListener end***/


    /**CompletionListener start***/
    @Override
    public void onSearch(String txt) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onSelect(String txt, int type, String extraData) {
        // TODO Auto-generated method stub
        
    }
    /**CompletionListener end***/

    /**OnItemClickListener start***/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        // TODO Auto-generated method stub
        
    }
    /**OnItemClickListener end***/

    /**TextWatcher start***/
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTextChanged(CharSequence text, int start,
            int lengthBefore, int lengthAfter) {
        // TODO Auto-generated method stub
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
    
    /**TextWatcher end***/
}