package com.amida.input;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;

@SpringBootTest
class InputApplicationTests {

	@Autowired
	private MessageCollector collector;

	@Autowired
	private Source source;

	@Test
	void contextLoads() {
	}

	@Test
	public void testPoetryInput() throws Exception {
		//listen for a message from source for 1 second
		Message message = this.collector.forChannel(this.source.output()).poll(1, TimeUnit.SECONDS);
		//get the payload
		String line = message.getPayload().toString();
		//confirm it is what is expected
		assertNotNull(line);
		assertTrue(line.length() >0);

	}
}
