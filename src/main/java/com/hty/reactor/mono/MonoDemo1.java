package com.hty.reactor.mono;

import reactor.core.publisher.Mono;

public class MonoDemo1 {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
    }
}
