package com.mobile.ladybrowser.provider;

import android.net.Uri;

public class BrowserContract {
    public static final String AUTHORITY = "com.mobile.ladybrowser";
    
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

}