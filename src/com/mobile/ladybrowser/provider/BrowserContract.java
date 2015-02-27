package com.mobile.ladybrowser.provider;

import android.net.Uri;

public class BrowserContract {
    public static final String AUTHORITY = "com.mobile.ladybrowser";
    
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    
    /**
     * A parameter for use when querying any table that allows specifying a limit on the number
     * of rows returned.
     */
    public static final String PARMA_LIMIT = "limit";
}