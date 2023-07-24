package com.hty.reactor.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        MyObservable myObservable = new MyObservable();
        MyObserver observer1 = new MyObserver("observer1");
        MyObserver observer2 = new MyObserver("observer2");

        //添加观察者
        myObservable.addObserver(observer1);
        myObservable.addObserver(observer2);

        //改变消息之后，所有的观察者都会收到通知
        myObservable.changeMessage("hello world");

    }
}
