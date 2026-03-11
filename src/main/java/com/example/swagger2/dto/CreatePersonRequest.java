package com.example.swagger2.dto;

import com.example.swagger2.mongomodel.Address;
import com.example.swagger2.mongomodel.Job;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreatePersonRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Age must be non-negative")
    private int age;

    @Valid
    private Address address;

    public CreatePersonRequest(String name, Job job, Address address, int age) {
        this.name = name;
        this.job = job;
        this.address = address;
        this.age = age;
    }

    @Valid
    private Job job;

    public CreatePersonRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
}

