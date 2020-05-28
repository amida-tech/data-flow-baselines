package com.amida.sink;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("server")
public class SinkProperties {
	 private String host = "hapi";

     private String port = "8080";
     
     @NotBlank
 	public String getHost() {
 		return host;
 	}

 	public void setHost(String host) {
 		this.host = host;
 	}

 	@NotBlank
 	public String getPort() {
 		return port;
 	}

 	public void setPort(String port) {
 		this.port = port;
 	}

}
