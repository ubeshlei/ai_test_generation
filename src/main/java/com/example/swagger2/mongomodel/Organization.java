package com.example.swagger2.mongomodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;
    private String name;
    private Address address;
    private String industry;

    public Organization() {}

    public Organization(String id, String name, Address address,String industry) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.industry = industry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
