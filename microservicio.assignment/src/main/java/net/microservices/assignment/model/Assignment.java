package net.microservices.assignment.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "assignment")
@JsonPropertyOrder({ "userId", "departmentId" })
public class Assignment implements Serializable {

    private static final long serialVersionUID = -184887648591563920L;
    @Id
    @NotNull
    private String userId;
    @NotNull
    private String departmentId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}