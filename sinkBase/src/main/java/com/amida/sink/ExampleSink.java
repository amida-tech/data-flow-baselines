package com.amida.sink;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import org.hl7.fhir.r4.model.Bundle;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import lombok.extern.slf4j.Slf4j;

@EnableBinding(Sink.class) @Slf4j
public class ExampleSink {


    //specify what type of message you are receiveing
    @StreamListener(Sink.INPUT)
    public void process(byte[] bytes){
    	String message = new String(bytes, StandardCharsets.UTF_8);
    	FhirContext r4 = FhirContext.forR4();
    	IParser jsonParser = r4.newJsonParser();
	    String serverBase = "http://hapi:8080/hapi-fhir-jpaserver/fhir";
	    IGenericClient client = r4.newRestfulGenericClient(serverBase);
    	FileInputStream file;
    	
    	// parsing json entries to fhir resource objects 
		Bundle bundle = jsonParser.parseResource(Bundle.class, message);
		
		// bundle should already have BundleType specified
		bundle.setType(Bundle.BundleType.TRANSACTION);
		
		// POSTing bundle to jpa fhir server
		Bundle resp = client.transaction().withBundle(bundle).execute();
		// printing JSON response from server
		System.out.println(jsonParser.setPrettyPrint(true).encodeResourceToString(resp));
    }
}