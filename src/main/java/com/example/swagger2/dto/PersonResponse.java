package com.example.swagger2.dto;

import com.example.swagger2.mongomodel.Address;
import com.example.swagger2.mongomodel.Job;

public class PersonResponse {
    private String id;
    private String name;
    private int age;
    private Address address;
    private Job job;

    public PersonResponse() {}

    public PersonResponse(String id, String name, int age, Address address, Job job) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.job = job;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
}


