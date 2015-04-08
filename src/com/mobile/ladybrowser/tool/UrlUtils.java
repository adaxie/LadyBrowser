package com.mobile.ladybrowser.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Patterns;
import android.webkit.URLUtil;

/**
 * Utility methods for Url manipulation
 */
public class UrlUtils {

    static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile(
            "(?i)" +
            "(" +
            "(?:http|https|file)://" +
            "|(?:inline|data|about|javascript):" +
            "|(?:.*:.*@)" +
            ")" +
            "(.*)");

    //Regular expression to strip http:// and optionally
    //the trailing slash.
    private static final Pattern STRIP_URL_PATTERN =
            Pattern.compile("^http://(.*?)/?$");

    /**
     * Strips the provided url of preceding "http://" and any trailing "/". Does not
     * strip "https://". If the provided string cannot be stripped, the original string
     * is returned.
     *
     * @param url a url to strip, like "http://www.google.com/"
     * @return a stripped url like "www.google.com", or the original string if it could
     *         not be stripped.
     */
    public static String stripUrl(String url) {
        if (url == null)
            return null;
        Matcher m = STRIP_URL_PATTERN.matcher(url);
        if (m.matches()) {
            return m.group(1);
        }
        return url;
    }

    /**
     * Attempts to determine whether user input is a URL or search terms.
     * Anything with a space is passed to search if canBeSearch is true.
     * @param canBeSearch If true, will return a search url if it isn't a valid
     *        URL. If false, invalid URLs will return null.
     * @return Original or modified URL
     * TODO: Will add search logic
     */
    public static String smartUrlFilter(String url, boolean canBeSearch) {
        String inUrl = url.trim();
        boolean hasSpace = inUrl.indexOf(' ') != -1;

        Matcher matcher = ACCEPTED_URI_SCHEMA.matcher(inUrl);
        if (matcher.matches()) {
            String scheme = matcher.group(1);
            String lcScheme = scheme.toLowerCase();
            if (!lcScheme.equals(scheme)) {
                inUrl = lcScheme + matcher.group(2);
            }
            if (hasSpace && Patterns.WEB_URL.matcher(inUrl).matches()) {
                inUrl = inUrl.replace(" ", "%20");
            }
            return inUrl;
        }
        if (!hasSpace) {
            if (Patterns.WEB_URL.matcher(inUrl).matches()) {
                return URLUtil.guessUrl(inUrl);
            }
        }
        return null;
    }

    public static String fixUrl(String inUrl) {
        int colon = inUrl.indexOf(':');
        boolean allLower = true;
        for (int i = 0; i < colon; i++) {
            char ch = inUrl.charAt(i);
            if (!Character.isLetter(ch)) {
                break;
            }
            allLower &= Character.isLowerCase(ch);
            if (i == colon - 1 && !allLower) {
                inUrl = inUrl.substring(0, colon).toLowerCase()
                        + inUrl.substring(colon);
            }
        }
        if (inUrl.startsWith("http://") || inUrl.startsWith("https://")) {
            return inUrl;
        }
        if (inUrl.startsWith("http:") || inUrl.startsWith("https:")) {
            if (inUrl.startsWith("http:/") || inUrl.startsWith("https:/")) {
                return inUrl.replaceFirst("/", "//");
            }
            return inUrl.replaceFirst(":", "://");
        }
        return inUrl;
    }

    //Returns the filtered URL. Cannot return null, but can return an empty string

    public static String filteredUrl(String inUrl) {
        if (inUrl == null) {
            return "";
        }
        if (inUrl.startsWith("content:")
                || inUrl.startsWith("browser:")) {
            return "";
        }
        return inUrl;
    }
}