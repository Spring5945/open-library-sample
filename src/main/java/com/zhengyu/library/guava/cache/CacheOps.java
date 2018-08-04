package com.zhengyu.library.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyu.nie on 2018/7/31.
 */
public class CacheOps {
    public static void main(String[] args) throws Exception {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            // 当guava cache中不存在，则会调用load方法
            @Override
            public String load(String key) {
                return key.concat("-reset");
            }
        };
        /** 通用 */
        normal(loader);
        /** LRU策略 */
        sizeLru(loader);
        timeLru(loader);
//        显式清除
//        任何时候，你都可以显式地清除缓存项，而不是等到它被回收：
//        个别清除：Cache.invalidate(key)
//        批量清除：Cache.invalidateAll(keys)
//        清除所有缓存项：Cache.invalidateAll()
        /** 清除监听 */
        removeListener(loader);


    }

    private static void removeListener(CacheLoader<String, String> loader) {
        RemovalListener<String, String> listener;
        listener = n -> {
            if (n.wasEvicted()) {
                String cause = n.getCause().name();
                System.out.println("listener:" + cause);
            }
        };

        LoadingCache<String, String> cacheListener;
        cacheListener = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(loader);
        cacheListener.getUnchecked("first");
        cacheListener.getUnchecked("second");
        cacheListener.getUnchecked("third");
        cacheListener.getUnchecked("last");
    }

    private static void timeLru(CacheLoader<String, String> loader) throws InterruptedException {
        /* 时间回收策略 */
//        expireAfterAccess(long, TimeUnit)：缓存项在给定时间内没有被读/写访问，则回收。请注意这种缓存的回收顺序和基于大小回收一样。
//        expireAfterWrite(long, TimeUnit)：缓存项在给定时间内没有被写访问（创建或覆盖），则回收。如果认为缓存数据总是在固定时候后变得陈旧不可用，这种回收方式是可取的。

        LoadingCache<String, String> cacheTimeLru;
        cacheTimeLru = CacheBuilder
                .newBuilder()
                // 写数据1s后重新加载缓存
                .refreshAfterWrite(1L, TimeUnit.SECONDS)
                // 最大缓存数量
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(loader);

        System.out.println(cacheTimeLru.getUnchecked("cachetime"));
        Thread.sleep(3000);
        System.out.println(cacheTimeLru.getIfPresent("cachetime"));
    }

    private static void sizeLru(CacheLoader<String, String> loader) {
        /* 空间回收策略 */
        // 设置最大缓存数量
        LoadingCache<String, String> cacheSizeLru;
        cacheSizeLru = CacheBuilder
                .newBuilder()
                // 写数据1s后重新加载缓存
                .refreshAfterWrite(1L, TimeUnit.SECONDS)
                // 最大缓存数量
                .maximumSize(2)
                .build(loader);

        cacheSizeLru.getUnchecked("first");
        cacheSizeLru.getUnchecked("second");
        cacheSizeLru.getUnchecked("third");
        cacheSizeLru.getUnchecked("forth");
        System.out.println(cacheSizeLru.getIfPresent("first"));
        System.out.println(cacheSizeLru.getUnchecked("first"));
    }

    private static void normal(CacheLoader<String, String> loader) throws InterruptedException {
        LoadingCache<String, String> cache;
        cache = CacheBuilder
                .newBuilder()
                // 写数据1s后重新加载缓存
                .refreshAfterWrite(1L, TimeUnit.SECONDS)
                .build(loader);
        System.out.println(cache.size());
        cache.put("test", "test");
        System.out.println(cache.getUnchecked("test"));
        System.out.println(cache.getUnchecked("hello"));
        System.out.println(cache.size());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.getUnchecked("test"));
    }

}
