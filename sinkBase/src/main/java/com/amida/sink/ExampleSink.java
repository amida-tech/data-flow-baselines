package com.amida.sink;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(Sink.class) @Slf4j
public class ExampleSink {


    //specify what type of message you are receiveing
    @StreamListener(Sink.INPUT)
    public void process(Message<String> message){

        //do something with the payoad
        log.info(message.getPayload());
    }
}