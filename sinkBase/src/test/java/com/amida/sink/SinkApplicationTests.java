package com.amida.sink;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@SpringBootTest
class SinkApplicationTests {

	@Autowired
	protected Sink sink;

	@Autowired
	protected ExampleSink exampleSink;

	@Test
	void contextLoads() {
	}

	@Ignore
	public void testUsageCostLogger() throws Exception {
		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		this.sink.input().send(MessageBuilder.withPayload("example text").build());
	}


	@EnableAutoConfiguration
	@EnableBinding(Sink.class)
	static class TestConfig {

		//override the bean for the sink and wrap it in a spy so we can confirm it received the data
		@Bean
		@Primary
		public ExampleSink getExampleSink() {
			return spy(new ExampleSink());
		}
	}
}
