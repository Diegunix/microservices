package net.microservices.department;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
public class DepartmentApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(DepartmentApplication.class, args);
    }
    
}