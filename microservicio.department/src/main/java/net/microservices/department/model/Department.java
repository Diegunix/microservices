package net.microservices.department.model;
 
import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 
@Document(collection = "departments")
@JsonPropertyOrder({"userId", "name"})
public class Department implements Serializable{
 
    private static final long serialVersionUID = -7788619177798333712L;
 
    @Id
    @NotNull 
    private String departmentId;
    @NotNull   
    private String name;
     
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }  
}