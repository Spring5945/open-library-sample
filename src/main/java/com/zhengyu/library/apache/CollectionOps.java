package com.zhengyu.library.apache;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.bag.HashBag;

import java.util.Arrays;
import java.util.List;

public class CollectionOps {
    public static void main(String[] args) throws Exception {

        /** hashbag */
        hashbag();
        /** CollectionUtils */
        collectionUtils();
    }


    private static void collectionUtils() {
        List<String> testStr = Arrays.asList("2", "1", "3");
        List<String> testStr2 = Arrays.asList("6", "4", "3");
        List<String> testStr3 = Arrays.asList("2", "1", "3");
        List<String> testStr4 = Arrays.asList("2", "1", "3", "3");
        System.out.println(CollectionUtils.get(testStr, 2));
        System.out.println(CollectionUtils.union(testStr, testStr2));

        System.out.println(CollectionUtils.select(testStr, o -> Integer.parseInt(o.toString()) > 1));
        System.out.println(CollectionUtils.find(testStr, o -> Integer.parseInt(o.toString()) > 1));
        System.out.println(CollectionUtils.containsAny(testStr, testStr2));
        System.out.println(CollectionUtils.exists(testStr, o -> Integer.parseInt(o.toString()) > 1));
        System.out.println(CollectionUtils.countMatches(testStr, o -> Integer.parseInt(o.toString()) > 1));
        System.out.println(CollectionUtils.isEqualCollection(testStr, testStr3));
        System.out.println(CollectionUtils.isSubCollection(testStr, testStr3));
        System.out.println(CollectionUtils.isNotEmpty(testStr));
        System.out.println(CollectionUtils.isEmpty(testStr));
        System.out.println(CollectionUtils.isFull(testStr));
        System.out.println(CollectionUtils.getCardinalityMap(testStr4));

    }

    private static void hashbag() {
        HashBag bag = new HashBag();
        bag.add("rabbit", 1);
        bag.add("fox", 1);
        bag.add("rabbit", 2);

        //rabbit count
        System.out.println(bag.getCount("rabbit"));
        //how many animals
        System.out.println(bag.uniqueSet().size());
    }
}
