package com.zhengyu.library.guava.concurrent;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhengyu.nie on 2018/7/31.
 */
public class ListenableFutureOps {

    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private static ExecutorService executorService = null;

    public static void main(String[] args) {
        /** Thread Pool */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                r -> {
                    Thread t = new Thread(r, "BasicRestServiceThread-" + threadNumber.getAndIncrement());
                    t.setDaemon(false);
                    return t;
                }, (r, executor) -> System.out.println("BasicRestServiceThreadPool线程池最大线程数已满,拒绝策略error报警"));
        executorService = TtlExecutors.getTtlExecutorService(threadPoolExecutor);

        //将ExecutorService装饰成ListeningExecutorService
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(
                executorService);

        //通过异步的方式计算返回结果
        ListenableFuture<String> future = listeningExecutorService.submit(() -> {
            System.out.println("call execute..");
            return "task success!";
        });

        //有两种方法可以执行此Future并执行Future完成之后的回调函数
        future.addListener(() -> {  //该方法会在多线程运算完的时候,指定的Runnable参数传入的对象会被指定的Executor执行
            try {
                System.out.println("result: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, listeningExecutorService);

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("callback result: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        }, listeningExecutorService);
    }

}
