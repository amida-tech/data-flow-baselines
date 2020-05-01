package com.amida.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(ExampleProcessorProperties.class)
public class ExampleProcessor {

	/**
	 * recieve messages of the speciied object type
	 * @param message
	 * @return
	 */
    @StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Message <String> messenger(Message < byte[] > message) {
		//if you want to add header values
		Map<String, String> extra = new HashMap<>();
		
		// get the payload
		byte[] payload = message.getPayload();
		
		//manipulate the payload however you need to 
		String out = new String(payload);
		
		//create a message with payload and any additional headers
		Message < String > m = MessageBuilder
			.withPayload(out)
			.copyHeaders(extra)
			.build();
		//send it on
		return m;
	}

}