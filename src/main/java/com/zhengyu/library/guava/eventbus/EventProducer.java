package com.zhengyu.library.guava.eventbus;

import com.google.common.eventbus.EventBus;

import java.time.LocalDateTime;

/**
 * Created by zhengyu.nie on 2018/7/31.
 */
public class EventProducer {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("zhengyu");

        eventBus.register(new EventConsumer());
        eventBus.post(new Event("test event listen", LocalDateTime.now()));
        eventBus.post("test string listen");
    }
}
