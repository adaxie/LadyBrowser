package com.mobile.ladybrowser.view;

import java.util.ArrayList;

import com.mobile.ladybrowser.R;

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
