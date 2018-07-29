package com.zhengyu.library.guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

public class StringOps {
    // 字符串连接器
    private static final Joiner skipNullsJoiner = Joiner.on(",").skipNulls();
    private static final Joiner useForNullJoiner = Joiner.on(",").useForNull("empty");

    // 字符串分割器
    private static final Splitter fixedLengthSplitter = Splitter.fixedLength(2);
    private static final Splitter limitSplitter = Splitter.on(",").limit(3);

    // 字符串匹配器
    private static final CharMatcher isNotCharMatcher = CharMatcher.isNot('c');
    private static final CharMatcher inRangeCharMatcher = CharMatcher.inRange('a', 'f');
    private static final CharMatcher predicateCharMatcher = CharMatcher.forPredicate(input -> {
        if (input.compareTo('b') > 0) return true;
        return false;
    });

    public static void main(String[] args) {
        /** joiner */
        String skipNullsString = skipNullsJoiner.join(Lists.newArrayList("a", null, "b"));
        String useForNullString = useForNullJoiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println(skipNullsString);
        System.out.println(useForNullString);

        /** splitter */
        List<String> fixedlengthSplitterList = fixedLengthSplitter.splitToList("aaa,b,c,,,f");
        List<String> limitSplitterList = limitSplitter.splitToList("aaa,b,c,,,f");
        System.out.println(fixedlengthSplitterList.get(0));
        limitSplitterList.forEach(item -> System.out.println(item));

        /** CharMatcher */
        System.out.println(inRangeCharMatcher.retainFrom("bcfz"));
        System.out.println(isNotCharMatcher.retainFrom("bcfz"));
        System.out.println(CharMatcher.ascii().retainFrom("bcf12321z^&^&$&d"));

        CharMatcher complexMatcher = isNotCharMatcher.and(inRangeCharMatcher);
        System.out.println(complexMatcher.retainFrom("asdfsafdsaffccdfc"));
        System.out.println(predicateCharMatcher.retainFrom("asdfsafdsaffccdfc"));
    }


}
