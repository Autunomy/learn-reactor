package com.hty.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

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

    public static void useZip(){
        //使用zip压缩合并响应式流
        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples");
        // 当两个Flux对象压缩在⼀起的时候，它将会产⽣⼀个新的发布元组的Flux，其中每个元组中都包含了来⾃每个源Flux的数据项
        // 这个合并后的Flux发出的每个条⽬都是⼀个Tuple2（⼀个容纳两个其他对象的容器对象）的实例，其中包含了来⾃每个源Flux的数据项，并保持着它们发布的顺序。
        Flux<Tuple2<String, String>> zippedFlux =
                Flux.zip(characterFlux, foodFlux);

        zippedFlux.subscribe(t -> {
            System.out.println(t.getT1() + "|" + t.getT2());
        });
        /**
         * 执行结果：
         * Garfield|Lasagna
         * Kojak|Lollipops
         * Barbossa|Apples
         */
    }

    public static void useZipMakeStream(){
        //使用zip压缩合并为自定义对象的响应式流
        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples");

        // 压缩成自定义对象
        Flux<String> zippedFlux =
                Flux.zip(characterFlux, foodFlux, (c, f) -> c + " eats " + f);
        zippedFlux.subscribe(System.out:: println);

        /**
         * 执行结果：
         * Garfield eats Lasagna
         * Kojak eats Lollipops
         * Barbossa eats Apples
         */
    }

    public static void useZipChooseFirst() throws InterruptedException {
        //选择第⼀个反应式类型进⾏发布
        Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
                .delaySubscription(Duration.ofMillis(100)); // 延迟100ms
        Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");
        // 选择第⼀个反应式类型进⾏发布
        Flux<String> firstFlux = Flux.first(slowFlux, fastFlux);
        firstFlux.subscribe(System.out::println);
        // 阻塞，等待结果
        Thread.sleep(100000);
        /**
         * 执行结果：
         * hare
         * cheetah
         * squirrel
         */
    }

}
