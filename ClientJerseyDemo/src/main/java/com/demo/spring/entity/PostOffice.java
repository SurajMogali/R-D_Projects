package com.demo.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostOffice {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("BranchType")
    private String branchType;

    @JsonProperty("DeliveryStatus")
    private String deliveryStatus;

    @JsonProperty("State")
    private String state;

    @JsonProperty("District")
    private String district;

    @JsonProperty("Pincode")
    private String pincode;

    // Getters and setters
    // You can generate them using your IDE or manually

    @Override
    public String toString() {
        return "PostOffice{" +
                "name='" + name + '\'' +
                ", branchType='" + branchType + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
