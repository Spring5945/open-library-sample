package com.zhengyu.library.apache;

import org.apache.commons.lang3.StringUtils;

public class StringLang3Ops {
    public static void main(String[] args) throws Exception {
        stringUtils();
    }

    private static void stringUtils() {
        String testString = "hello";
        String testString2 = "world";
        String testString3 = "hel";
        String testString4 = "h,el,lo";
        System.out.println(StringUtils.containsAny("r", testString, testString2, testString3));
        System.out.println(StringUtils.countMatches("l", testString));
        System.out.println(StringUtils.equals(testString, testString2));
        System.out.println(StringUtils.trim(testString));
        System.out.println(StringUtils.isNotBlank(testString));
        System.out.println(StringUtils.isNotEmpty(testString));
        System.out.println(StringUtils.isEmpty(testString));
        System.out.println(StringUtils.isBlank(testString));
        System.out.println(StringUtils.lowerCase(testString));
        System.out.println(StringUtils.upperCase(testString));
        System.out.println(StringUtils.join(testString, "-a", "-b"));
        System.out.println(StringUtils.trim(testString));
        System.out.println(StringUtils.trimToEmpty(testString));
        System.out.println(StringUtils.trimToNull(testString));
        System.out.println(StringUtils.split(testString4, ",").length);
    }
}
