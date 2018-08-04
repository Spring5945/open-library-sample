package com.zhengyu.library.guava.basicdatatypes;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Objects;

public class BasicDataTypesOps {
    public static void main(String[] args) {

        /** Integer */
        ints();

        /** Boolean */
        booleans();

        /** Objects */
        objects();
        /** Preconditions */
        preconditions();

        /** Optional */
        optional();


    }

    private static void optional() {
        Optional.fromNullable("test").isPresent();
    }

    private static void preconditions() {
        Preconditions.checkNotNull(null, "");
        Preconditions.checkArgument(1 > 0, "");
    }

    private static void objects() {
        Objects.toString("test", "default");
        Objects.requireNonNull("test", "exception mes");

        com.google.common.base.Objects.hashCode(new Object(), new Object());
        com.google.common.base.Objects.equal(new Object(), new Object());
    }

    private static void booleans() {
        System.out.println(Booleans.compare(true, false) > 0);
        System.out.println(Booleans.compare(false, true) > 0);
    }

    private static void ints() {
        List<Integer> asList = Ints.asList(1, 3, 2, 4, 5);
        System.out.println(asList);
        System.out.println(Ints.contains(Ints.toArray(asList), 3));
        System.out.println(Ints.compare(2, 1) > 0);
        System.out.println(Ints.concat(new int[]{1, 2}, new int[]{3, 4, 5}).length);
        System.out.println(Ints.max(3, 2, 5, 6, 2, 1, 9, 15, 3));
        System.out.println(Ints.min(3, 2, 5, 6, 2, 1, 9, 15, 3));
        System.out.println(Ints.tryParse("3a"));
        System.out.println(Ints.join(",", 1, 3, 2));
    }
}
