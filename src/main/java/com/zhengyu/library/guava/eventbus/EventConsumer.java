package com.zhengyu.library.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created by zhengyu.nie on 2018/7/31.
 */
public class EventConsumer {

    @Subscribe
    public void listen(Event event) {
        System.out.println("receive message: " + event.getEventMes() + "  /event time:" + event.getEventTime());
    }

    @Subscribe
    public void listen(String message) {
        System.out.println("receive message: " + message);
    }
}
