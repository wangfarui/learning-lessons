package com.wfr.learning.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * 基于Java {@link java.util.Observer} 示例
 *
 * @author wangfarui
 * @since 2022/6/30
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable eventObservable = new EventObservable();
        // 添加观察者（监听者）
        eventObservable.addObserver(new EventObserver());
        // 发送消息（事件）
        eventObservable.notifyObservers("Hello World");
    }

    static class EventObservable extends Observable {
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            if (arg instanceof EventObject) {
                EventObject eventObject = (EventObject) arg;
                System.out.println("收到事件: " + eventObject.getSource());
            }
        }
    }
}
