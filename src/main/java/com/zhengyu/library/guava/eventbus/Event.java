package com.zhengyu.library.guava.eventbus;

import java.time.LocalDateTime;

/**
 * Created by zhengyu.nie on 2018/7/31.
 */
public class Event {
    String eventMes;
    LocalDateTime eventTime;

    public Event(String eventMes, LocalDateTime eventTime) {
        this.eventMes = eventMes;
        this.eventTime = eventTime;
    }

    public String getEventMes() {
        return eventMes;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }
}
