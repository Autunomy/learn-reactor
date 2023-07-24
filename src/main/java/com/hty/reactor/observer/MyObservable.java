package com.hty.reactor.observer;


import java.util.Observable;
import java.util.Observer;

//jdk1.8中基于Observer/Observable接口而实现的观察者模式：
public class MyObservable extends Observable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void changeMessage(String message) {
        this.message = message;
        // 标记状态已经改变
        setChanged();
        // 通知所有观察者
        notifyObservers(message);
    }


}
