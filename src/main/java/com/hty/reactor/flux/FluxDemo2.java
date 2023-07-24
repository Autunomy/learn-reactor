package com.hty.reactor.flux;

import reactor.core.publisher.Flux;

import java.time.Duration;

//响应式流的合并
public class FluxDemo2 {
    public static void main(String[] args) throws InterruptedException {
        useMergeWith();
    }

    public static void useMergeWith() throws InterruptedException {
        //使用mergeWith合并响应式流
        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbosa")
                .delayElements(Duration.ofMillis(500)); // 每500毫秒发布⼀个数据

        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250)) // 订阅后250毫秒后开始发布数据
                .delayElements(Duration.ofMillis(500)); // 每500毫秒发布⼀个数据

        // 使⽤mergeWith()⽅法，将两个Flux合并，合并过后的Flux数据项发布顺序与源Flux的发布时间⼀致
        // Garfield Lasagna Kojak Lollipops Barbosa Apples
        Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);

        mergedFlux.subscribe(System.out::println);

        // 阻塞，等待结果
        Thread.sleep(100000);
    }

}
