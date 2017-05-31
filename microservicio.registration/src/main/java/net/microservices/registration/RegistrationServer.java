package net.microservices.registration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaServer
@EnableAutoConfiguration
public class RegistrationServer {
 
    public RegistrationServer(){
        //For Spring
    }
 
    public static void main(String[] args) {
      
        new SpringApplicationBuilder(RegistrationServer.class).web(true).run(args);
    }
}