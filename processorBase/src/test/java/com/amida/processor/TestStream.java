package com.amida.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.springframework.messaging.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestStream {


    @Autowired
    private Processor processor;

    @Autowired
    private MessageCollector messageCollector;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testUsageCostProcessor() throws Exception {
		String testString = "test string";
		Map<String, String> headers = new HashMap<>();
		headers.put("testkey", "testValue");
		Message < byte[] > m = MessageBuilder
			.withPayload(testString.getBytes())
			.copyHeaders(headers)
			.build();
		this.processor.input().send(m);
		Message < ? > message = this.messageCollector.forChannel(this.processor.output()).poll(1, TimeUnit.SECONDS);
		String out = message.getPayload().toString();
		Map<String, ?> respHeaders = message.getHeaders();
		//check payload
		assertEquals(testString, out);
		//check header
		assertTrue(headers.get("testkey").equals(respHeaders.get("testkey")));
		
	}

}
