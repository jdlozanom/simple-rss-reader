package com.jdlozanom.simplerssreader.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

    //Fetch for first <img> tag and returns it
    public static String getFirstImage(String input) {
        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m = p.matcher(input);
        if (m.find()) {
            return "https:" + m.group(1);
        } else return null;
    }

    // Returns input string without <img> tags
    public static String removeImageTags(String input) {
        return input.replaceAll("(<(/)img>)|(<img.+?>)", "");
    }
}
