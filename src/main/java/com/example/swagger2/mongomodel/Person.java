package com.example.swagger2.mongomodel;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.ModelAttribute;

@Document(collection = "persons")
public class Person {

    @Id
    private String id;

    private String name;
    private int age;
    private Address address;
    private Job job;

    public Person() {}

    public Person(String id, String name, int age, Address address, Job job) {
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


