package com.hx.study.reactor.example.consumer;

import java.util.function.Supplier;

public class FluxConsumer implements Supplier<String> {

    @Override
    public String get() {
        return "123";
    }
}
