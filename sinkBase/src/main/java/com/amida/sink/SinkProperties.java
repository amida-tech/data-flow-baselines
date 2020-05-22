package com.amida.sink;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("server")
public class SinkProperties {
	 private String ip = "localhost";

     private int port = 8080;
     
     public String getIp() {
    	 return ip;
     }
     
     public void setIp(String newIp) {
    	 this.ip = newIp;
     }
     
     public int getPort() {
    	 return port;
     }
     
     public void setPort(int newPort) {
    	 this.port = newPort;
     }

}
