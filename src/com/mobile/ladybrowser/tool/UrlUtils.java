package com.mobile.ladybrowser.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods for Url manipulation
 */
public class UrlUtils {
    
    static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile(
            "(?i)" + //switch on case insensitive matching
            "(" +   //begin group for schema
            "");
    
    //Regular expression to strip http:// and optionally
    //the trailing slash.
    private static final Pattern STRIP_URL_PATTERN =
            Pattern.compile("^http://(.*?)/?$");
    
    private UrlUtils() {/*cannot be instantiated*/}
    
    /**
     * Strips the provided url of preceding "http://" and any trailing "/". Does not
     * strip "https://". If the provided string cannot be stripped, the original string
     * is returned.
     * 
     * @param url a url to strip, like "http://www.google.com"
     * @return a stripped url like "www.google.com", or the original string if it could
     *         not be stripped.
     */
    public static String stripUrl(String url) {
        if (url == null)
            return null;
        Matcher m = STRIP_URL_PATTERN.matcher(url);
        if (m.matches()) {
            return m.group(1);
        } else {
            return url;
        }
    }
    
    public static String fixUrl(String url) {
        //Converting the url to lower case
        //duplicates functionality in smartUrlFiler().
        //However, changing all current callers of fixUrl to
        //call smartUrlFilter in addition may have unwanted
        //consequences, and is deferred for now.
        int colon = url.indexOf(':');
        boolean allLower = true;
        for (int index = 0; index < colon; index++) {
            char ch = url.charAt(index);
            if (!Character.isLetter(ch)) {
                break;
            }
            allLower &= Character.isLowerCase(ch);
            if (index ==colon -1 && !allLower) {
                url = url.substring(0, colon).toLowerCase()
                        + url.substring(colon);
            }
        }
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        }
        if (url.startsWith("http:") ||
                url.startsWith("https:")) {
            if (url.startsWith("http:/") || url.startsWith("https:/")) {
                url = url.replaceFirst("/", "//");
            } else {
                url = url.replaceFirst(":", "://");
            }
        }
        return url;
    }
}