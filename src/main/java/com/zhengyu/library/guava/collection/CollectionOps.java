package com.zhengyu.library.guava.collection;

import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionOps {
    public static void main(String[] args) {

        /** Multiset */
        multiSet();

//        为什么要用immutable对象？immutable对象有以下的优点：
//　　　　1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
//　　　　2.线程安全的：immutable对象在多线程下安全，没有竞态条件
//　　　　3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
//　　　　4.可以被使用为一个常量，并且期望在未来也是保持不变的

        /** ImmutableList */
        ImmutableList();

        /** ImmutableMap */
        ImmutableMap();
        // 双向map
        Bimap();

        /** ImmutableTable */
        ImmutableTable();


    }

    private static void ImmutableTable() {
        ImmutableTable<Integer, String, String> immutableTable = ImmutableTable.<Integer, String, String>builder()
                .put(17, "child", "2")
                .put(18, "child", "1")
                .build();
        System.out.println(immutableTable.row(18));

        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("17", "jnt", 80);
        table.put("18", "snt", 90);
        table.put("17", "jnt", 100);
        table.put("18", "snt", 70);
        System.out.println(table.get("17", "jnt"));
    }

    private static void Bimap() {
        ImmutableBiMap<String, String> immutableBiMap = ImmutableBiMap.<String, String>builderWithExpectedSize(5).put("a", "1").build();
        ImmutableBiMap<String, String> inverseImmutableBiMap = immutableBiMap.inverse();
        System.out.println(immutableBiMap);
        System.out.println(inverseImmutableBiMap);
    }

    private static void ImmutableMap() {
        System.out.println(ImmutableMap.of("1", "a", "2", "b"));
        ImmutableMap<String, String> immutableMap = ImmutableMap.<String, String>builder().put("1", "a").put("2", "b").build();
        System.out.println(immutableMap);
    }

    private static void ImmutableList() {
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");

        List<String> unmodifiableList = Collections.unmodifiableList(list);
        ImmutableList<String> copyList = ImmutableList.copyOf(list);
        ImmutableList<String> buildList = ImmutableList.<String>builderWithExpectedSize(5).add("a").add("b").add("c").build();
        ImmutableList<String> ofList = ImmutableList.of("a", "b", "c");

        System.out.println(unmodifiableList);
        System.out.println(copyList);

        list.add("d");
        System.out.println(unmodifiableList);
        System.out.println(copyList);
    }

    private static void multiSet() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        System.out.println(multiset.count("a"));
        System.out.println(multiset.count("b"));
        System.out.println(multiset.elementSet());
        System.out.println(multiset.toString());
    }
}
