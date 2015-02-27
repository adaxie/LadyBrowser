package com.mobile.ladybrowser.view;

import java.util.ArrayList;

import com.mobile.ladybrowser.R;
import com.mobile.ladybrowser.provider.BrowserProvider.OmniboxSuggestions;
import com.mobile.ladybrowser.search.SearchEngine;
import com.mobile.ladybrowser.setting.BrowserSettings;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * adapter to wrap multiple cursors for url/search completions.
 */
public class SuggestionsAdapter extends BaseAdapter implements Filterable,
       OnClickListener {
    
    public static final int TYPE_BOOKMARK = 0;
    public static final int TYPE_HISTORY = 1;
    public static final int TYPE_SUGGEST_URL = 2;
    public static final int TYPE_SEARCH = 3;
    public static final int TYPE_SUGGEST = 4;
    
    private static final String[] COMBINED_PROJECTION = {
        OmniboxSuggestions._ID,
        OmniboxSuggestions.TITLE,
        OmniboxSuggestions.URL,
        OmniboxSuggestions.IS_BOOKMARK
    };
    
    private static final String COMBINED_SELECTION = "(url LIKE ? OR url LIKE ? OR url LIKE ? OR url LIKE ? OR title LIKE ?)";
    
    final Context mContext;
    final CompletionListener mListener;
    final int mLinesPortrait;
    final Object mResultsLock = new Object();
    BrowserSettings mSettings;
    
    public SuggestionsAdapter(Context ctx, CompletionListener listener) {
        mContext = ctx;
        mListener = listener;
        mSettings = BrowserSettings.getInstance();
        mLinesPortrait = mContext.getResources().getInteger(R.integer.max_suggest_lines_portrait);
    }

    /***BaseAdapter start***/
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        return null;
    }
    /***BaseAdapter end***/

    /***Filterable start***/
    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        return null;
    }
    /***Filterable end***/

    /***OnClickListener start***/
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        
    }
    /***OnClickListener end***/
    
    interface CompletionListener {
        public void onSearch(String txt);
        public void onSelect(String txt, int type, String extraData);
    }
    
    /**
     * data object to hold suggestion values
     */
    public class SuggestItem {
        public String title;
        public String url;
        public int type;
        public String extra;
        
        public SuggestItem(String text, String u, int t) {
            title = text;
            url = u;
            type = t;
        }
    }
    
    /**
     * sorted list of results of a suggestion query
     */
    class SuggestionResults {
        ArrayList<SuggestItem> items;
        //count per type
        int[] counts;
        
        SuggestionResults() {
            items = new ArrayList<SuggestItem>(24);
            counts = new int[5];
        }
        
        int getTypeCount(int type) {
            return counts[type];
        }
        
        void addResult(SuggestItem item) {
            int ix = 0;
            while ((ix < items.size()) && (item.type >= items.get(ix).type))
                ix++;
            items.add(ix, item);
            counts[item.type]++;
        }
        
        int getLineCount() {
            return Math.min(mLinesPortrait, items.size());
        }
        
        public String toString() {
            if (items == null) return null;
            if (items.size() == 0) return "[]";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                SuggestItem item = items.get(i);
                sb.append(item.type + ": " + item.title);
                if (i < items.size() - 1) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }
    }
    
    abstract class CursorSource {
        Cursor mCursor;
        
        boolean moveToNext() {
            return mCursor.moveToNext();
        }
        
        public int getCount() {
            return (mCursor != null) ? mCursor.getCount() : 0;
        }
        
        public void close() {
            if (mCursor != null) {
                mCursor.close();
            }
        }
        
        public abstract void runQuery(CharSequence constraint);
        public abstract SuggestItem getItem();
    }
    
    class SuggestCursor extends CursorSource {

        @Override
        public void runQuery(CharSequence constraint) {
            if (mCursor != null) {
                mCursor.close();
            }
            SearchEngine searchEngine = mSettings.getSearchEngine();
            if (!TextUtils.isEmpty(constraint)) {
                mCursor = searchEngine.getSuggestions(mContext, constraint.toString());
                if (mCursor != null) {
                    mCursor.moveToFirst();
                }
            } else {
                if (searchEngine.wantsEmptyQuery()) {
                    mCursor = searchEngine.getSuggestions(mContext, "");
                }
                mCursor = null;
            }
        }

        @Override
        public SuggestItem getItem() {
            if (mCursor != null) {
                String title = mCursor.getString(
                        mCursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
                String url = mCursor.getString(
                        mCursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_2_URL));
                String uri = mCursor.getString(
                        mCursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_INTENT_DATA));
                int type = TextUtils.isEmpty(url) ? TYPE_SUGGEST : TYPE_SUGGEST_URL;
                SuggestItem item = new SuggestItem(title, url, type);
                item.extra = mCursor.getString(
                        mCursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_INTENT_EXTRA_DATA));
                return item;
            }
            return null;
        }
        
    }
}