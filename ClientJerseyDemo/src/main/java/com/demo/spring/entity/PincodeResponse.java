package com.demo.spring.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
 public class PincodeResponse {
    @JsonProperty("Message")
    private String message;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("PostOffice")
    private List<PostOffice> postOffices;

    // Getters and setters
    // You can generate them using your IDE or manually

    @Override
    public String toString() {
        return "PincodeResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", postOffices=" + postOffices +
                '}';
    }
}