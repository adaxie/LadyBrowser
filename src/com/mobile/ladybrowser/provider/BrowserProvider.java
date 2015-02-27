package com.mobile.ladybrowser.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class BrowserProvider extends ContentProvider {

    public static interface OmniboxSuggestions {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                BrowserContract.AUTHORITY_URI, "omnibox_suggestions");
        public static final String _ID = "_id";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String IS_BOOKMARK = "bookmark";
    }
    
    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}