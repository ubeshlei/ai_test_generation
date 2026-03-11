package com.example.swagger2.dto;

import com.example.swagger2.mongomodel.Address;
import jakarta.validation.constraints.NotBlank;

public class CreateOrganizationRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private Address address;

    private String industry;

    public CreateOrganizationRequest() {}

    public CreateOrganizationRequest(String name, String industry, Address address) {
        this.name = name;
        this.industry = industry;
        this.address = address;
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
