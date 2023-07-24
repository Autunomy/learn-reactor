package com.hty.reactor.observer;


import java.util.Observable;
import java.util.Observer;

//jdk1.8中基于Observer/Observable接口而实现的观察者模式：
public class MyObserver implements Observer {
    private String name;

    public MyObserver(String name){
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            System.out.println("Observer " + this.name + " 收到通知: " + (String) arg);
        }
    }
}
