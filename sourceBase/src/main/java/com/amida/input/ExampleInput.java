package com.amida.input;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@EnableBinding(Source.class) @Slf4j
public class ExampleInput {

    @Autowired
    private Source source;


    //some text to send from the source
    private String[] text = {"Although I can see him still,", "The freckled man who goes", "To a grey place on a hill",
                            "In grey Connemara clothes", "At dawn to cast his flies,", "It is long sincce I began",
                            "To call up to the eyes", "This wise and simple man."};
    
    //in this example we send on a schedule but this function 
    //could also be triggered by somthing else in the app
    //for instance a submission from a web page might go to a controller
    // and then be sent into this function to be processed as part of a stream                     
    @Scheduled(fixedDelay = 1000)
    public void sendMessage(){
        log.info("sending text"); 
        //get whatever you are going to send
        String payload = this.text[new Random().nextInt(text.length)];
        
        //create a message
        Message<String> message = MessageBuilder
            .withPayload(payload)
            .build();

        //send message
        this.source.output().send(message);
        log.info("text sent");
    }

}