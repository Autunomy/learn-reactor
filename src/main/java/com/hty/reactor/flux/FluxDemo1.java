package com.hty.reactor.flux;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//flux响应式流的创建方式
public class FluxDemo1 {
    public static void main(String[] args) {
    }

    public static void createByJust(){
        //使用just来创建一个响应式类型
        Flux<String> fruitFlux = Flux.just("Apple","Orange","Grape","Banana","Strawberry");

        // 调用just或其他方法只是声明数据流，数据流并没有发出，只有进行订阅之后才会触发数据流，不订阅什么都不会发生的。
        // 添加一个订阅者，subscribe的方法参数相当于是一个Consumer  对响应式中的数据进行输出
        fruitFlux.subscribe(
                f -> System.out.println("Here's some fruit: " + f)
        );
    }

    public static void createByCollections(){
        //根据数组创建一个响应式
        String[] fruits = new String[] {
                "Apple", "Orange", "Grape", "Banana", "Strawberry" };
        Flux<String> fruitFlux = Flux.fromArray(fruits);

        //根据集合
        List<String> list = Arrays.asList(fruits);
        Flux.fromIterable(list); // 集合

        //根据流
        Stream<String> stream = list.stream();
        Flux.fromStream(stream); // stream流
    }
}
