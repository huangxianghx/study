package com.hx.study.reactor.example;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Hot {
    public static void main(String[] args) throws InterruptedException {
        final Flux<Long> source = Flux.interval(Duration.of(10, ChronoUnit.SECONDS))
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe();
        Thread.sleep(5000);
        source
                .toStream()
                .forEach(System.out::println);
    }
}
