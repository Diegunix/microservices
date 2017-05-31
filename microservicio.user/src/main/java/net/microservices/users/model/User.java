package net.microservices.users.model;
 
import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 
@Document(collection = "users")
@JsonPropertyOrder({"userId", "name", "address"})
public class User implements Serializable{
 
    private static final long serialVersionUID = -7788619177798333712L;
 
    @Id
    @NotNull 
    private String userId;
    @NotNull   
    private String name;

    private String address;
     
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }   
}