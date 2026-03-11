package com.example.swagger2.dto;

import com.example.swagger2.mongomodel.Address;

public class OrganizationResponse {
    private String id;
    private String name;
    private Address address;
    private String industry;

    public OrganizationResponse() {}

    public OrganizationResponse(String id, String name, Address address, String industry) {
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
