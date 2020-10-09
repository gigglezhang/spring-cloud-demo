package com.jc.webfluxdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author jincheng.zhang
 */
@RestController
@Slf4j
public class TestWebfluxController {
    // 阻塞5秒钟
    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignored) {
        }
        return "some string";
    }


    @GetMapping("/hello")
    public String hello(){
        log.info("begin ------- normal");
        String result = createStr();
        log.info("end ----------- normal");
        return result;
    }

    @GetMapping("/helloUser")
    public Mono<String> helloUser(){
        log.info("begin -------  webFlux");
        Mono<String> mono = Mono.fromSupplier(this::createStr);
        log.info("end ----------- webFlux");
        return mono;
    }

    @GetMapping(value = "/testPush", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> testPush(){
        log.info("begin -------  webFlux");
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj( i ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "this is " + i;
        }));
        log.info("end ----------- webFlux");
        return result;
    }
}
