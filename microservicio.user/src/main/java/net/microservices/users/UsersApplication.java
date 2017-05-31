package net.microservices.users;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
 

@Configuration
@SpringBootApplication
public class UsersApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }
    
}